import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class lives here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lives extends Actor
{
    private GreenfootImage image = getImage(); 
    public Lives(){
        image.scale(40, 32);
    }

    public void setTransparency(int t){
        image.setTransparency(t);
    }
}
