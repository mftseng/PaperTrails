package entities;

import main.Game;

import java.awt.*;

import static utilz.Constants.EnemyConstants.ERASER_HEIGHT;
import static utilz.Constants.EnemyConstants.ERASER_WIDTH;
import static utilz.Constants.ObstacleConstants.*;
import static utilz.Constants.ObstacleConstants.GONE;

public class Fire extends Obstacle{
    public Fire(float xPos, float yPos, Game game) {
        super(xPos, yPos, game);
        innitHitBox(xPos, yPos, 45 * Game.SCALE, 30 * Game.SCALE);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animations[FIRE][aniIndex], (int) (xPos - 25), (int) (yPos), 75, 50, null);
        drawHitbox(g);
    }




}
