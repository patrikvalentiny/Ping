import greenfoot.*;

/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author Patrik Valentiny (PM), Jakov Klaric, Abdulkader Alomar, Ignas Klimas
 * @version 1.2
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    private int level = 1;
    private Paddle playerPaddle = new Paddle(100,20);
    private Paddle2 paddle2 = new Paddle2(100,15);
    private Score score = new Score();
    private Ball ball = new Ball();
    private int[] ballHistory = new int[2];

    private Lives life1 = new Lives();
    private Lives life2 = new Lives();
    private Lives life3 = new Lives();
    private int lives = 3;

    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted)
    {
        // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK);

            addObject(ball, WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(playerPaddle, 80, WORLD_HEIGHT - 50);
            addObject(paddle2, 60, 50);
            addObject(score,442, 37);

            level = 1;
            showLevel();

            worldGraphics(background);
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }

    private void worldGraphics(GreenfootImage bg){
        int dashSize = 40;
        int dashSpace = dashSize / 2;
        int liveY = 30;
        bg.setColor(Color.BLACK);
        bg.fill();

        bg.setColor(Color.WHITE);    
        bg.fillRect(0, 0, 10, WORLD_HEIGHT);
        bg.fillRect(WORLD_WIDTH-10, 0, WORLD_WIDTH, WORLD_HEIGHT);
        bg.fillRect(0, 0, WORLD_WIDTH, 10);
        bg.fillRect(0, WORLD_HEIGHT - 10, WORLD_WIDTH, WORLD_HEIGHT);
        
        addObject(life1, WORLD_WIDTH/2 - 100, liveY);
        addObject(life2, WORLD_WIDTH/2 - 150, liveY);
        addObject(life3, WORLD_WIDTH/2 - 200, liveY);

        for (int i=0;i<WORLD_WIDTH/dashSize;i++){
            bg.fillRect(i*dashSize + dashSpace, WORLD_HEIGHT/2 - 3, dashSize - dashSpace, 6);
        }
    }

    public void act(){
        showLevel();
        ballTrail();
        if (lives == 0){
            Greenfoot.setWorld(new GameOverWorld(getLevel()));
        }
    }

    public Paddle getPlayerPaddle(){
        return playerPaddle;
    }

    public Paddle2 getAIPaddle(){
        return paddle2;
    }

    public void showLevel(){
        score.setImage(new GreenfootImage("Level " + level, 30, Color.WHITE, null));
        //showText("Level " + level, 452, 37);
    }

    public void updateLevel(){
        level++;
    }

    public int getLevel(){
        return level;
    }

    public void setLevelRestart(){
        level = 1;
    }

    private void ballPosHistory(){
        ballHistory[0] = ball.getX();
        ballHistory[1] = ball.getY();
    }

    private void ballTrail(){
        addObject(new TrailBall(), ballHistory[0], ballHistory[1]);
        ballPosHistory();
    }

    /*
     * THis code removes lives when the ball hits the floor.
     */
    public void killWombat(){
        if(lives == 3){
            ((Lives)life1).setTransparency(100);
            lives--;
        }else if(lives == 2){
            ((Lives)life2).setTransparency(100);
            lives--;
        }else if (lives == 1){
            ((Lives)life3).setTransparency(100);
            lives--;
        }
    }
}

