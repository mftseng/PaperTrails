package entities;

import main.Game;

import java.awt.*;

import static utilz.Constants.ObstacleConstants.GONE;

public class Gone extends Obstacle{
    public Gone( float xPos, float yPos, Game game) {
        super( xPos, yPos, game);

    }
    @Override
    public void render(Graphics g) {
        g.drawImage(animations[GONE][aniIndex], (int) (xPos), (int) (yPos), 0, 0, null);
        drawHitbox(g);
    }



}
