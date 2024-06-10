package ui;

import finals.Final;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;

public class PauseOverLay {
    private BufferedImage backGroundImage;
    private int bgX,bgY,bgW,bgH;
    private SoundButton musicButton, sfxButton;

    public PauseOverLay(){
        loadBackGroundImage();
        createSoundButtons();
    }

    private void createSoundButtons() {
        int soundX = (int)(450 * Final.SCALE);
        int musicY = (int)(140 * Final.SCALE);
        int sfxY = (int)(186 * Final.SCALE);
        musicButton = new SoundButton(soundX,musicY,SOUND_SIZE,SOUND_SIZE);
        sfxButton = new SoundButton(soundX,sfxY,SOUND_SIZE,SOUND_SIZE);
    }

    private void loadBackGroundImage() {
        backGroundImage = LoadSave.getSpriteAtlas(LoadSave.PAUSE_MENU);
        bgW =(int) (backGroundImage.getWidth() * Final.SCALE);
        bgH =(int) (backGroundImage.getHeight() * Final.SCALE);
        bgX = Final.GAME_WIDTH / 2 - bgW / 2;
        bgY =(int)( 25 * Final.SCALE);
    }

    public void update(){
        musicButton.update();
        sfxButton.update();
    }
    public void draw(Graphics g){
        //Back ground
        g.drawImage(backGroundImage,bgX,bgY,bgW,bgH,null);

        //Sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);

    }
    public void mouseDragged(MouseEvent e ){

    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e,musicButton)){
            musicButton.setMousePressed(true);
        }else if (isIn(e,sfxButton)){
            sfxButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e,musicButton)){
            if (musicButton.isMousePressed()){
                musicButton.setMuted(!musicButton.isMuted());
            }
        }else if (isIn(e,sfxButton)){
            if (sfxButton.isMousePressed()){
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        }

        musicButton.resatBools();
        sfxButton.resatBools();
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);

        if (isIn(e,musicButton)){
            musicButton.setMouseOver(true);
        }else if (isIn(e,sfxButton)){
            sfxButton.setMouseOver(true);
        }
    }
    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX() , e.getY());
    }

}
