package entities;

import levels.LevelManager;
import main.Game;

import java.awt.*;


import static utilz.HelpMethods.CanMoveHere;


public class MovingEntities extends Entity{
    private LevelManager levelManager;
    private boolean inAir;
    private float airSpeed = 0f;
    private float gravity = .15f* Game.SCALE;
    public MovingEntities(float x, float y, int width, int height, Game game) {
        super(x, y, width, height, game);
        levelManager = new LevelManager(game);
    }

    public void applyGravity(){
        Rectangle[] lvlDat = levelManager.getLvlData();
        inAir = CanMoveHere(hitbox.x, hitbox.y + airSpeed, (int) hitbox.width, (int) hitbox.height, lvlDat);
        if (!inAir){
            if(CanMoveHere(hitbox.x, hitbox.y + 5,(int) hitbox.width, (int) hitbox.height, lvlDat)){
                inAir = true;
                airSpeed = 0;
                hitbox.y += 1;
                y= hitbox.y;
            }
        }
    }

    public boolean getInAir(){
        return inAir;
    }
}
