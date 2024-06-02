package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {//בשביל השחקן והאויבים שירשו ממנה כדי שנוכל לשמור פה דברים משותפים לשניהם
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected Rectangle2D.Float hitBox;

    public Entity(float x,float y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    protected void drawHitBox(Graphics g){
        // בשביל הבאגים של התנגשויות
        g.setColor(Color.RED);
        g.drawRect((int) hitBox.x,(int)hitBox.y, (int)hitBox.width,(int)hitBox.height);
    }

    protected void initHitBox(float x,float y,float width,float height) {
        this.hitBox = new Rectangle2D.Float(x,y,width,height);

    }
//    protected void updateHitBox(){
//        this.hitBox.x = (int)x;
//        this.hitBox.y = (int)y;
//    }
    public Rectangle2D.Float getHitBox(){
        return hitBox;
    }

    public abstract void update();//for the players&enemy

    public void render(Graphics g)//עיבוד השחקן
    {

    }


}
