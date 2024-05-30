package main;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel){

         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.add(gamePanel);
         this.setResizable(false);//לא ניתן לשינוי גודל החלון
         this.pack();//מתאים את עצמו לחלון שקיים בGamePanel כדי ליצור מצב של מסך גדול יותר
         this.setLocationRelativeTo(null);
         this.addWindowFocusListener(new WindowFocusListener() {
             @Override
             public void windowGainedFocus(WindowEvent e) {

             }

             @Override
             public void windowLostFocus(WindowEvent e) {
                 gamePanel.getGame().windowFocusLost();
             }
         });
         this.setVisible(true);
    }
}
