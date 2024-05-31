package levels;
import Finals.Final;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Finals.Final.*;
import static utilz.LoadSave.*;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprites;
    private Level levelOne;

    public LevelManager(Game game){
        this.game = game;
        importOutSideSprites();
        this.levelOne = new Level(getLevelData());
    }

    private void importOutSideSprites() {
        final int SIZE= 32;
        BufferedImage img = LoadSave.getSpriteAtlas(LEVEL_ATLAS);
        this.levelSprites = new BufferedImage[48];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                int index = (i*12) + j;
                levelSprites[index] = img.getSubimage(j*SIZE,i*SIZE,SIZE,SIZE);
            }
        }
    }


    public void draw(Graphics g){
        for (int y = 0; y < TILES_IN_HEIGHT; y++) {
            for (int x = 0; x < TILES_IN_WIDTH; x++) {
                int index = levelOne.getSpriteIndex(x,y);
                g.drawImage(levelSprites[index],TILES_SIZE*x,TILES_SIZE*y,TILES_SIZE,TILES_SIZE,null);
            }
        }



    }
    public void update(){

    }
}
