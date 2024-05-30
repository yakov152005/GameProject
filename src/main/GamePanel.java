package main;
import Finals.Final;
import Inputs.KeyBoardInputs;
import Inputs.MouseInputs;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {

        this.mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize() {
        Dimension size = new Dimension(Final.WIDTH,Final.HEIGHT);
        setPreferredSize(size);
    }

    public void updateGame(){

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            game.render(g);
        }catch (NullPointerException e){
            System.out.println();
        }

    }
    public Game getGame(){
        return game;
    }
}
