import greenfoot.*;

/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author Patrik Valentiny (PM), Jakov Klaric, Abdulkader Alomar, Ignas Klimas
 * @version 1
 */
public class Paddle extends Actor
{
    private int width;
    private int height;
    private int dx;
    private int lastDx;
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 2;
        createImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        tryChangeDirection();
        changeDirection();
        setLocation(getX() + dx, getY());
        //((PingWorld)getWorld()).showText(""+dx,200,300);
    }    

    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void tryChangeDirection()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() + width/2 >= (getWorld().getWidth()) - 10 || getX() - width/2  <= 0 + 10)
        {
            //Change our 'x' direction to the inverted direction:
            dx = dx * -1;
        }
    }

    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.WHITE);
        image.fill();
        setImage(image);
    }

    private void changeDirection(){
        String key = Greenfoot.getKey();
        ((PingWorld)getWorld()).showText(key, 100, 100);
        if (key != null){
            if (key.equals("left")){
                if (dx>0){
                    dx *= -1; 
                } else if (dx==0){
                    dx = -1;  
                }
            } else if (key.equals("right")){
                if (dx < 0){
                    dx *= -1;
                } else if (dx ==0){
                    dx = 1;
                }
            } else if (key.equals("up")){
                if (dx>0 && dx <5){
                    dx += 1;
                } else if (dx<0 && dx > -5){
                    dx -= 1; 
                } else if (dx == 0){
                    dx = lastDx;
                }
            } else if (key.equals("down")){
                if (dx>0){
                    if (dx != 0){
                        lastDx = dx;
                        dx -= 1;
                    }
                } else if (dx < 0){
                    if (dx != 0){
                        lastDx = dx;
                        dx += 1;
                    }  
                }
            }
        }
    }
}
