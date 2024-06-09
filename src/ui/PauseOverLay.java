package ui;

import finals.Final;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PauseOverLay {
    private BufferedImage backGroundImage;
    private int bgX,bgY,bgW,bgH;

    public PauseOverLay(){
        loadBackGroundImage();
    }

    private void loadBackGroundImage() {
        backGroundImage = LoadSave.getSpriteAtlas(LoadSave.PAUSE_MENU);
        bgW =(int) (backGroundImage.getWidth() * Final.SCALE);
        bgH =(int) (backGroundImage.getHeight() * Final.SCALE);
        bgX = Final.GAME_WIDTH / 2 - bgW / 2;
        bgY = 50;
    }

    public void update(){

    }
    public void draw(Graphics g){
        g.drawImage(backGroundImage,bgX,bgY,bgW,bgH,null);


    }
    public void mouseDragged(MouseEvent e ){

    }

    public void mousePressed(MouseEvent e) {

    }


    public void mouseReleased(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

}
