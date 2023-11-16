package entities;

import levels.LevelManager;
import main.Game;
import utilz.LoadSave;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.ObstacleConstants.ERASER;
import static utilz.HelpMethods.CanMoveHere;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Eraser extends Obstacle{
    private float x;
    private float y;
    private int aniIndex, enemyState;
    private int aniTick, aniSpeed = 5;
    private float xDrawOffset = 45 * Game.SCALE;
    private float yDrawOffset = 35 * Game.SCALE;
    private BufferedImage[][] eraserArray;
    private LevelManager levelManager;

    private boolean inAir;
    private float airSpeed = 0f;
    private float gravity = .15f * Game.SCALE;

    private float xSpeed = 2f;

    public Eraser(float xPos, float yPos, Game game) {
        super(xPos, yPos, game);
        this.xPos =xPos;
        this.yPos = yPos;
        innitHitBox(x, y , (int)((ERASER_WIDTH-40)*Game.SCALE), (int)((ERASER_HEIGHT+15)*Game.SCALE));
        levelManager = new LevelManager(game);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animations[ERASER][aniIndex], (int) (xPos - 35), (int) (yPos - 15), ERASER_WIDTH, ERASER_HEIGHT, null);
        drawHitbox(g);
//        System.out.println("x: "+ hitbox.x + "y: " + hitbox.y);
        }

    @Override
    public void update(){
//        updatePos();
        updateHitbox();
        updateAnimationTickOBBY();
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



    public void updatePos() {
        Rectangle[] lvlDat = levelManager.getLvlData(); // Access the lvlDat array
        if (lvlDat != null) {
            inAir = CanMoveHere(hitbox.x, hitbox.y + airSpeed, (int) hitbox.width, (int) hitbox.height, lvlDat);
        }
//        System.out.print(!CanMoveHere((int) (hitbox.x + xSpeed), hitbox.y, (int) hitbox.width, (int) hitbox.height, lvlDat));

        if (!CanMoveHere((int) (hitbox.x + xSpeed), hitbox.y, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
            xSpeed = xSpeed * -1;
        } else {
            hitbox.x += xSpeed;
            x = hitbox.x;
        }
        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
                hitbox.y += airSpeed;
                y = hitbox.y;
                airSpeed += gravity;
//                System.out.println("hitbox" + hitbox.y);
//                System.out.println(y);
            }
            else {
                airSpeed = 0;
            }
        }

    }




}
