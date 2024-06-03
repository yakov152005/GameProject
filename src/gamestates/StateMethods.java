package gamestates;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods { // כל מחלקה שתירש את הממשק הזה תהיה חייבת לממש את המתודות שקיימות פה
    public void update();
    public void draw(Graphics g);
    public void mouseClicked(MouseEvent e );
    public void mousePressed(MouseEvent e );
    public void mouseReleased(MouseEvent e );
    public void mouseMoved(MouseEvent e );
    public void keyPressed(KeyEvent e );
    public void keyReleased(KeyEvent e);
}
