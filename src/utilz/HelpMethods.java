package utilz;

import levels.LevelManager;
import main.Game;

import java.awt.*;

public class HelpMethods {
    public static boolean CanMoveHere(float x, float y, int width, int height, Rectangle[] lvlDat) {
        //Boundaries of the game
        if (x < 0 || x + width > Game.GAME_WIDTH)
            return false;
        if (y < 0 || y > Game.GAME_HEIGHT)
            return false;
        //Checks for collision of level objects
        for (int i = 0; i < lvlDat.length; i++) {
            if ((lvlDat[i].x + lvlDat[i].width >= (int) x) &&
                    (lvlDat[i].y + lvlDat[i].height >= (int) y) &&
                    ((int) x + width >= lvlDat[i].x) &&
                    ((int) y + height >= lvlDat[i].y)) {
                return false; // Collision detected
            }
        }
        return true; // No collision detected
    }



}
