package ui;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SoundButton extends PauseButton{
    private BufferedImage[][] soundImages;
    private boolean mouseOver,mousePressed;
    private boolean muted;
    private int rowIndex,colIndex;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadSoundImages();
    }

    private void loadSoundImages() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.SOUND_BUTTON);
        soundImages = new BufferedImage[2][3];
        for (int y = 0; y < soundImages.length; y++) {
            for (int x = 0; x < soundImages[y].length ; x++) {
                soundImages[y][x] = temp.getSubimage(x * SOUND_SIZE_DEFAULT,y * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT,SOUND_SIZE_DEFAULT );

            }
        }
    }
    public void update(){
        if (muted){
            rowIndex =1;
        }else {
            rowIndex = 0;
        }

        colIndex = 0;
        if (mouseOver){
            colIndex = 1;
        }
        if (mousePressed){
            colIndex = 2;
        }
    }

    public void resatBools(){
        mouseOver = false;
        mousePressed = false;
    }


    public void draw(Graphics g){
        g.drawImage(soundImages[rowIndex][colIndex],getX(),getY(),getWidth(),getHeight(),null);
    }

    public BufferedImage[][] getSoundImages() {
        return soundImages;
    }

    public void setSoundImages(BufferedImage[][] soundImages) {
        this.soundImages = soundImages;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
