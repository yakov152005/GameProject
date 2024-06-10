package ui;
import java.awt.*;
public class PauseButton {
    private int x,y,width,height;
    private Rectangle bounds;

    public PauseButton(int x, int y , int width,int height){
        this.x = x;
        this.y = y;
        this . width = width;
        this.height = height;
        createBounds();
    }

    private void createBounds() {
        bounds = new Rectangle(x,y,width,height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
