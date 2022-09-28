import greenfoot.*;

/**
 * Write a description of class IntroWorld here.
 * 
 * @author Patrik Valentiny (PM), Jakov Klaric, Abdulkader Alomar, Ignas Klimas
 * @version 1.2
 */
public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    private int timer = 0;
    private GreenfootImage background = getBackground();
    private boolean up = true;
    /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        Greenfoot.start();
    }
    
    public void act()
    {   
        background.clear();
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
        if (timer == 255){
            up = false;
        } else if (timer == 0){
            up = true;
        }
        if (up){
            timer++;
        } else{
            timer--;
        }
        background.setColor(new Color(255 - timer, 255 - timer, 255 - timer));
        background.fill();
        background.drawImage(new GreenfootImage("PONG", 80, new Color(timer, timer, timer), null), 150, 200);
        background.drawImage(new GreenfootImage("Play for the lives of wombats", 20, new Color(timer, timer, timer), null), 140, 300);
        background.drawImage(new GreenfootImage("Hit <enter> to start game", 30, new Color(timer, timer, timer), null), 120, WORLD_HEIGHT / 2);
        background.drawImage(new GreenfootImage("Controls: \n- LEFT and RIGHT arrow keys control the paddle`s direction\n- UP and DOWN arrow keys control the paddle`s speed", 15, new Color(timer, timer, timer), null), 80, WORLD_HEIGHT / 2 + 100);
        
    }
    
    
}
