package utilz;

import levels.LevelManager;
import main.Game;

import java.awt.*;

public class HelpMethods {
    private static boolean topOverlap;
    private static boolean rightOverlap;
    private static boolean bottomOverlap;
    private static boolean leftOverlap;

    public static boolean CanMoveHere(float x, float y, int width, int height, Rectangle[] lvlDat) {
        // Boundaries of the game
        if (x < 0 || x + width > Game.GAME_WIDTH || y < 0 || y > Game.GAME_HEIGHT)
            return false;


        // Check for collision with level objects
        for (int i = 0; i < lvlDat.length; i++) {
            leftOverlap = (x + width > lvlDat[i].x);
            rightOverlap = (x < lvlDat[i].x + lvlDat[i].width);
            bottomOverlap = (y + height > lvlDat[i].y);
            topOverlap = (y < lvlDat[i].y + lvlDat[i].height);

            if (leftOverlap && rightOverlap && bottomOverlap && topOverlap) {
                return false; // Collision detected
            }
        }

        return true; // No collision detected
    }

    public static boolean getTopOverlap(){
        return topOverlap;
    }

    public static boolean getBottomOverlap(){
        return bottomOverlap;
    }

    public static boolean getRightOverlap(){
        return rightOverlap;
    }

    public static boolean getLeftOverlap(){
        return leftOverlap;
    }



//    public static boolean CanMoveHere(float x, float y, int width, int height, Rectangle[] lvlDat) {
//        return CanMoveLeft(x, lvlDat) && CanMoveRight(x, width, lvlDat) && CanMoveUp(y, lvlDat) && CanMoveDown(y, height, lvlDat);
//    }
//
//    public static boolean CanMoveLeft(float x, Rectangle[] lvlDat) {
//        if (x < 0)
//            return false;
//        for (int i = 0; i < lvlDat.length; i++) {
//            if (lvlDat[i].x + lvlDat[i].width >= (int) x){
//                return false; // Collision detected
//            }
//        }
//        return true;
//    }
//    public static boolean CanMoveRight(float x, int width, Rectangle[] lvlDat) {
//        if ( x + width > Game.GAME_WIDTH)
//            return false;
//        for (int i = 0; i < lvlDat.length; i++) {
//            if ((int) x + width >= lvlDat[i].x){
//                return false; // Collision detected
//            }
//        }
//        return true;
//    }
//    public static boolean CanMoveUp(float y,Rectangle[] lvlDat) {
//        if (y < 0)
//            return false;
//        for (int i = 0; i < lvlDat.length; i++) {
//            if (lvlDat[i].y + lvlDat[i].height >= (int) y){
//                return false; // Collision detected
//            }
//        }
//        return true;
//    }
//    public static boolean CanMoveDown( float y, int height, Rectangle[] lvlDat) {
//        if (y > Game.GAME_HEIGHT)
//            return false;
//        for (int i = 0; i < lvlDat.length; i++) {
//            if ((int) y + height >= lvlDat[i].y){
//                return false; // Collision detected
//            }
//        }
//        return true;
//    }
//
}
