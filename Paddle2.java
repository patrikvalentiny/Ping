import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Paddle2 here.
 * 
 * @author Abdulkader Alomar
 * @version 1
 */
public class Paddle2 extends Actor
{
    private int width;
    private int height;
    private int dx;
    public Paddle2(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        createImage();
        // Paddle constructer *//
    }

    public void act()
    {
        respawn();
        setLocation(getX() + dx, getY());
    }

    private void respawn()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(isAtEdge())
        {
            int rnd = Greenfoot.getRandomNumber(2);
            int y = Greenfoot.getRandomNumber(280)+55;
            if(rnd == 0){
                setLocation(10,y);
                dx = 1;
            } else if(rnd == 1) {
                setLocation(getWorld().getWidth() - 10, y);
                dx = -1;
            }
            width = Greenfoot.getRandomNumber(41)+40;
            height = Greenfoot.getRandomNumber(11)+10;
            createImage();
        }
    }

    private void createImage()
    { // creating the image for the paddle //
        GreenfootImage image1 = new GreenfootImage(width, height);
        image1.setColor(Color.WHITE);
        image1.fill();
        setImage(image1);
    }
}
