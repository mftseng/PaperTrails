package utilz;

import entities.Obstacle;
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
    private static int objectIndex;

    public int getObjectIndex() {
        return objectIndex;
    }

    public static boolean CanMoveHere(float x, float y, int width, int height, Rectangle[] lvlDat) {
        //Boundaries of the game
        if (x < 0 || x + width > Game.GAME_WIDTH)
            return false;
        if (y < 0 || y > Game.GAME_HEIGHT)
            return false;
        //Checks for collision of level objects
        for (int i = 0; i < lvlDat.length; i++) {
            if (lvlDat[i] != null) {
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

    public static int CanMoveHereObject(float x, float y, int width, int height, Rectangle[] lvlDat) {
        //Checks for collision of level objects
        for (int i = 0; i < lvlDat.length; i++) {
            if (lvlDat[i] != null) {
                leftOverlap = (lvlDat[i].x + lvlDat[i].width >= (int) x);
                topOverlap = (lvlDat[i].y + lvlDat[i].height >= (int) y);
                rightOverlap = ((int) x + width >= lvlDat[i].x);
                bottomOverlap = ((int) y + height >= lvlDat[i].y);
                if (leftOverlap && topOverlap && rightOverlap && bottomOverlap) {
                    return i; // Collision detected
                }
            }
        }
        return -1; // No collision detected
    }

    public static int onObstacle(float x, float y, int width, int height, Obstacle[] lvlDat) {
        for (int i = 0; i < lvlDat.length; i++) {
            if (lvlDat[i] != null) {
                leftOverlap = (lvlDat[i].getHitbox().x + lvlDat[i].getHitbox().width >= (int) x);
                topOverlap = (lvlDat[i].getHitbox().y + lvlDat[i].getHitbox().height >= (int) y);
                rightOverlap = ((int) x + width >= lvlDat[i].getHitbox().x);
                bottomOverlap = ((int) y + height >= lvlDat[i].getHitbox().y);
                if (leftOverlap && topOverlap && rightOverlap && bottomOverlap) {
                    return i;
                }
            }
        }
        return -1;
    }
}


