package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {//בשביל השחקן והאויבים שירשו ממנה כדי שנוכל לשמור פה דברים משותפים לשניהם
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    private  BufferedImage[][] allAnimation;

    public Entity(float x,float y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public abstract void update();//for the players&enemy

    public void render(Graphics g)//עיבוד השחקן
    {

    }


}
