package entities;

import main.Game;

import java.awt.*;

import static utilz.Constants.EnemyConstants.ERASER_HEIGHT;
import static utilz.Constants.EnemyConstants.ERASER_WIDTH;
import static utilz.Constants.ObstacleConstants.*;
import static utilz.Constants.ObstacleConstants.GONE;

public class Gem extends Obstacle{
    private boolean collected = false;

    public Gem(float xPos, float yPos, Game game) {
        super(xPos, yPos, game);
        innitHitBox(xPos, yPos, 50 * Game.SCALE, 15 * Game.SCALE);
    }

    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(animations[GEM][aniIndex], (int) (xPos-25), (int) (yPos - 15), 75, 46, null);
        }
        else{
            setHitbox(0,0);
            g.drawImage(animations[GONE][aniIndex], 0, 0, 0, 0, null);
        }
        drawHitbox(g);
    }

    public void setCollected(){
        collected = true;
    }
    public boolean getCollected(){
        return collected;
    }

}
