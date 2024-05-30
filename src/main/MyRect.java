package main;
import java.awt.*;
import java.util.Random;

public class MyRect {
    int x,y,w,h;
    int xDir = 1, yDir =1;
    Color color;
    Random random = new Random();

    public MyRect(int x, int y){
        this.x = x;
        this.y = y;
        w = random.nextInt(50);
        h =w;
        color = newColor();
    }
    public void updateRect(){
        this.x += xDir;
        this.y += yDir;

        if (( x + w) > 400 || x < 0){
            xDir *= -1;
            color = newColor();
        }
        if (( y + h) > 400 || y < 0){
            yDir *= -1;
            color = newColor();
        }
    }
    private Color newColor(){
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,w,h);
    }

    /*private Color color = new Color(150,20,90);
    private Random random;
    private ArrayList<MyRect> rectangle = new ArrayList<>();
    public void spawnRect(int x,int y){
        rectangle.add(new MyRect(x,y));
         for (MyRect rect: rectangle){
            rect.updateRect();
            rect.draw(g);
        }
    }
        updateRectangle();
        g.setColor(color);
        g.fillRect((int) xDelta,(int) yDelta,200,50);


    }
    private void updateRectangle(){
        xDelta += xDir;
        if (xDelta > Final.WIDTH || xDelta < 0){
            xDir *= -1;
            color = getRGBcolor();
        }
       yDelta += yDir;
        if (yDelta > Final.WIDTH || yDelta < 0){
            yDir *= -1;
            color = getRGBcolor();
        }
    }
    private Color getRGBcolor(){
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }

    //  gamePanel.spawnRect(e.getX(),e.getY()); for mouse
    */
}
