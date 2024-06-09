package gamestates;

import finals.Final;
import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods{
    public final int SIZE_MENU_BUTTON = 3;
    private MenuButton[] buttons = new MenuButton[SIZE_MENU_BUTTON];
    private BufferedImage backGroundImage;
    private int menuX,menuY,menuWidth,menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackGround();
    }

    private void loadBackGround() {
        backGroundImage = LoadSave.getSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backGroundImage.getWidth() * Final.SCALE);
        menuHeight = (int) (backGroundImage.getHeight() * Final.SCALE);
        menuX = (Final.GAME_WIDTH / 2) - (menuWidth / 2);
        menuY = 100; // (int) (45 / Final.SCALE);

    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Final.GAME_WIDTH / 2,(int)(150 * Final.SCALE),0, GameState.PLAYING);
        buttons[1] = new MenuButton(Final.GAME_WIDTH / 2,(int)(220 * Final.SCALE),1, GameState.OPTIONS);
        buttons[2] = new MenuButton(Final.GAME_WIDTH / 2,(int)(290 * Final.SCALE),2, GameState.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton menuButton : buttons){
            menuButton.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backGroundImage,menuX,menuY,menuWidth,menuHeight,null);
        for (MenuButton menuButton : buttons){
            menuButton.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton menuButton : buttons){
            if (isIn(e,menuButton)){
                menuButton.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton menuButton : buttons){
            if (isIn(e,menuButton)){
                if (menuButton.isMousePressed()){
                    menuButton.applyGameState();
                }
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton menuButton : buttons){
            menuButton.resetBooleans();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton menuButton : buttons){
            menuButton.setMouseOver(false);
        }
        for (MenuButton menuButton : buttons){
            if (isIn(e, menuButton)) {
                menuButton.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_ENTER){
//            GameState.state = GameState.PLAYING;
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
