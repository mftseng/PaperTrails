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
        //Boundaries of the game
        if (x < 0 || x + width > Game.GAME_WIDTH)
            return false;
        if (y < 0 || y > Game.GAME_HEIGHT)
            return false;
        //Checks for collision of level objects
        for (int i = 0; i < lvlDat.length; i++) {
//            leftOverlap = (lvlDat[i].x + lvlDat[i].width >= (int) x) && (lvlDat[i].x <= (int) x);
//            topOverlap = (lvlDat[i].y + lvlDat[i].height >= (int) y) && ((int) y >=  lvlDat[i].y);
//            rightOverlap = ((int) x + width >= lvlDat[i].x) && ((int) x <= lvlDat[i].x + lvlDat[i].width);
//            bottomOverlap = ((int) y + height >= lvlDat[i].y) && ((int) y + height <= lvlDat[i].y + lvlDat[i].height);
            leftOverlap = (lvlDat[i].x + lvlDat[i].width >= (int) x);
            topOverlap = (lvlDat[i].y + lvlDat[i].height >= (int) y);
            rightOverlap = ((int) x + width >= lvlDat[i].x);
            bottomOverlap = ((int) y + height >= lvlDat[i].y);


            if (
                    leftOverlap
                            && //left side collision
                    topOverlap
                            && //top side collision
                    rightOverlap
                            && //right side collision
                    bottomOverlap//bottom side collision

            )
        {
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


}
