package utilz;

import finals.Final;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height,int [][] levelData){
        if (!isSolid(x,y,levelData)){
            if (! isSolid(x+width,y+height,levelData)){
                if (!isSolid(x+width,y,levelData)){
                    if (!isSolid(x,y+height,levelData)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static boolean isSolid(float x, float y,int [][] levelData){
        if (x < 0 || x >= Final.GAME_WIDTH){
            return true;
        }
        if (y< 0 || y >= Final.GAME_HEIGHT){
            return true;
        }

        float xIndex = x/Final.TILES_SIZE;
        float yIndex = y/Final.TILES_SIZE;

        int value = levelData[(int)yIndex ][(int) xIndex];

        if (value >= 48 || value < 0 || value !=11){
            return true;
        }else {
            return false;
        }
    }
}
