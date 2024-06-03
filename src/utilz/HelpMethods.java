package utilz;

import finals.Final;

import java.awt.geom.Rectangle2D;

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
    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox , float xSpeed){
        int currentTile = (int) (hitbox.x / Final.TILES_SIZE); // מחשב על איזה אריח נוכחי אנחנו נמצאים במיקום הX
        if (xSpeed > 0){
            // התנגשות של הקיר מצד ימין
            int tileXPos = currentTile * Final.TILES_SIZE; // פיקסל עבור האריח הנוכחי
            int xOffSet = (int) (Final.TILES_SIZE - hitbox.width);
            return tileXPos + xOffSet -1;
        }else {
            //התנגשות מצד שמאל
            return currentTile * Final.TILES_SIZE;
        }

    }
    public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox , float airSpeed){
        int currentTile = (int) (hitbox.y / Final.TILES_SIZE); // מחשב על איזה אריח נוכחי אנחנו נמצאים במיקום הy
        if (airSpeed > 0 ){
            // Falling - touching floor
            int tileYPos = currentTile * Final.TILES_SIZE; // פיקסל עבור האריח הנוכחי
            int yOffSet = (int) (Final.TILES_SIZE - hitbox.height);
            return tileYPos + yOffSet -1;
        }else {
            // Jumping
            return currentTile * Final.TILES_SIZE;
        }
    }
    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
        // Check the pixel below bottomLeft and bottomRight
        if (!isSolid(hitBox.x, hitBox.y + hitBox.height +1,levelData)){
            if (!isSolid(hitBox.x + hitBox.width , hitBox.y + hitBox.height +1,levelData)){
                return false;
            }
        }
        return true;
    }

}
