package gamestates;
import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static finals.Final.*;
import static finals.Final.SCALE;

public class Playing extends State implements StateMethods{
    private Player player;
    private LevelManager levelManager;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        try {
            levelManager = new LevelManager(game);
            player = new Player(200, 200,(int)(WIDTH_CAPTAIN * SCALE), (int)( HEIGHT_CAPTAIN * SCALE));
            player.loadLevelData(levelManager.getLevelOne().getLevelData());
        }catch (NullPointerException e){
            System.out.println(" ");
        }catch (Exception e ){
            System.out.println();
        }
    }
    @Override
    public void update() {
        levelManager.update();
        player.update();

    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            this.player.setAttacking(true);
        }
        if (e.getButton() == MouseEvent.BUTTON3){
            this.player.setAttackingJump(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                this.player.setUp(true);
                break;
            case KeyEvent.VK_A:
                this.player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                this.player.setDown(true);
                break;
            case KeyEvent.VK_D:
                this.player.setRight(true);
                break;
            case KeyEvent.VK_1:
                this.player.setAttackingJump2(true);
                break;
            case KeyEvent.VK_SPACE:
                this.player.setJump(true);
                break;
            case KeyEvent.VK_CONTROL:
                this.player.setHit(true);
                break;
            case KeyEvent.VK_ESCAPE:
                GameState.state = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                this.player.setUp(false);
                break;
            case KeyEvent.VK_A:
                this.player.setLeft(false);
                break;
            case KeyEvent.VK_S:
                this.player.setDown(false);
                break;
            case KeyEvent.VK_D:
                this.player.setRight(false);
                break;
            case KeyEvent.VK_1:
                this.player.setAttackingJump2(false);
                break;
            case KeyEvent.VK_SPACE:
                this.player.setJump(false);
                break;
            case KeyEvent.VK_CONTROL:
                this.player.setHit(false);
                break;
        }
    }

    public void windowFocusLost(){
        player.resetDirBoolean();

    }
    public Player getPlayer(){
        return player;
    }
}
