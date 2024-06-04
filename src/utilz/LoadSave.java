package utilz;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static finals.Final.TILES_IN_HEIGHT;
import static finals.Final.TILES_IN_WIDTH;

public class LoadSave {
    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String MENU_BUTTON_ATLAS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage image = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (IllegalArgumentException e){
            System.out.println("The File NOT FOUND!");
        }finally {
            try {
                is.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        return image;
    }
    public static int[][] getLevelData(){
        int[][] levelData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x,y));
                int value = color.getRed();
                if (value >= 48 ){
                    value = 0;
                }
                levelData[y][x] = value;
            }
        }
        return levelData;
    }
}
