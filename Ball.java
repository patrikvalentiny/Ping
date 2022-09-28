import greenfoot.*;

/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author Patrik Valentiny (PM), Jakov Klaric, Abdulkader Alomar, Ignas Klimas
 * @version 1
 */
public class Ball extends Actor
{
    private static final int BALL_SIZE = 25;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;
    private static final int LEVEL_UP_TRESHOLD = 10; // how many bounces does it take to level up

    private int speed;
    private int delay;
    private int bounceDelay = 0;
    private int bounceCounter = 0;

    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        createImage();
        init();
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
        ballImage.setColor(Color.WHITE);
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkRestart();
            checkBounceOffPlayerPaddle();
            checkBounceOffAIPaddle();
            speed = ((PingWorld)getWorld()).getLevel() + 1;
            if (bounceDelay>0){
                bounceDelay--;
            }
        }
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 + 10 || getX() >= getWorld().getWidth() - BALL_SIZE/2 -10);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2 + 10);
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2 - 10);
    }

    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides()){
            revertHorizontally();
            bounceDelay = 35;
            Greenfoot.playSound("Bounce.wav");
        }
    }

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling()){
            revertVertically();
            bounceDelay = 35;
            Greenfoot.playSound("Pan_hit.wav");
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            init();
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            ((PingWorld)getWorld()).setLevelRestart();
            ((PingWorld)getWorld()).killWombat();
            Greenfoot.playSound("Death.wav");
            //Greenfoot.setWorld(new GameOverWorld(((PingWorld)getWorld()).getLevel()));
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
    }

    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
    }

    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        delay = DELAY_TIME;
        bounceCounter = 0;
        int rnd;
        do{
            rnd = Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2;
        } while (rnd > 80 && rnd < 100);
        setRotation(rnd); 
         
    }

    private void checkBounceOffPlayerPaddle(){
        if(intersects(((PingWorld)getWorld()).getPlayerPaddle()) && bounceDelay == 0){
            Greenfoot.playSound("Bonk.wav");
            revertVertically();

            bounceCounter++;
            bounceDelay = 40;

            if((bounceCounter % LEVEL_UP_TRESHOLD == 0 && bounceCounter != 0)){
                ((PingWorld)getWorld()).updateLevel();
                Greenfoot.playSound("Sheesh.wav");
            }
        }
    }

    private void checkBounceOffAIPaddle(){
        Paddle2 paddle = ((PingWorld)getWorld()).getAIPaddle();
        if(intersects(paddle) && bounceDelay == 0){
            if ((getRotation() > 180)){
                revertVertically();
                Greenfoot.playSound("Bonk.wav");
                bounceDelay = 40;
            }
        }
    }
}
