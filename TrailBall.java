import greenfoot.*;

/**
 * A TrailBall is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author Patrik Valentiny (PM)
 * @version 1
 */
public class TrailBall extends Actor
{
    private static final int BALL_SIZE = 25;
    private static final int START_LIFE = 17; // determines how many balls will create the "motion blur"
    
    private int life = START_LIFE;
    
    private GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
    
    public TrailBall()
    {
        createImage();   
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
        ballImage.setColor(Color.WHITE);
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);
    }

    /**
     * Act - do whatever the TrailBall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (life == 0){
            ((PingWorld)getWorld()).removeObject(this);
        } else {
            life--;    
            fader();
        }
    }   
    private void fader(){
        int value = (255 / START_LIFE) * (life);
        ballImage.setTransparency(value);
    }
}
