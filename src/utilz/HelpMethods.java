package utilz;

import levels.Level;
import levels.LevelManager;
import main.Game;

import java.awt.*;

import static utilz.Constants.PlayerConstants.GetSpriteAmount;

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
            if(lvlDat[i] != null) {
                leftOverlap = (lvlDat[i].x + lvlDat[i].width >= (int) x);
                topOverlap = (lvlDat[i].y + lvlDat[i].height >= (int) y);
                rightOverlap = ((int) x + width >= lvlDat[i].x);
                bottomOverlap = ((int) y + height >= lvlDat[i].y);
                if (leftOverlap && topOverlap && rightOverlap && bottomOverlap) {
                    return false; // Collision detected
                }
            }
        }
        return true; // No collision detected
    }





}
