package entities;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.EnemyConstants.ERASER_HEIGHT;
import static utilz.Constants.EnemyConstants.ERASER_WIDTH;
import static utilz.Constants.ObstacleConstants.*;
import static utilz.Constants.ObstacleConstants.GONE;

public class Pencil extends Obstacle{

    public Pencil(float xPos, float yPos, Game game) {
        super(xPos, yPos, game);
        innitHitBox((xPos) * Game.SCALE, yPos, 30 * Game.SCALE, 90 * Game.SCALE);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animations[PENCIL][aniIndex], (int) (xPos - 40), (int)yPos, 95, 75, null);
        drawHitbox(g);
    }

    @Override
    protected void updateAnimationTickOBBY() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
                if (aniIndex >= 6) {
                    aniIndex = 0;
                }
            }
    }
}


