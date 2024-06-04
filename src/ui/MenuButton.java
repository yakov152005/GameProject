package ui;
import gamestates.GameState;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
    private int xPos, yPos,rowIndex,index;
    private final int X_OFF_CENTER = B_WIDTH / 2;
    private GameState state;
    private BufferedImage[] images;
    private boolean mouseOver,mousePressed;
    private Rectangle bounds;


    public MenuButton(int xPos, int yPos, int rowIndex, GameState state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImages();
        initBounds();

    }

    private void initBounds() {
        bounds = new Rectangle(xPos - X_OFF_CENTER, yPos,B_WIDTH,B_HEIGHT);
    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTON_ATLAS);
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * B_WIDTH_DEFAULT,rowIndex * B_HEIGHT_DEFAULT , B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics g){
        g.drawImage(images[index],xPos - X_OFF_CENTER, yPos,B_WIDTH,B_HEIGHT,null);
    }
    public void  update(){
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed){
                index = 2;
        }
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void applyGameState(){
        GameState.state = state;
    }
    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }
}
