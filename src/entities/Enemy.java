package entities;

import levels.LevelManager;
import main.Game;
import utilz.LoadSave;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.CanMoveHere;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Type;

public class Enemy extends MovingEntities{
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

    public Enemy(float x, float y,  int enemyState, Game game) {
        super(x, y, ERASER_WIDTH, ERASER_HEIGHT, game);
        this.enemyState = enemyState;
        levelManager = new LevelManager(game);
        innitHitBox(x, y ,(int)((ERASER_WIDTH-40)*Game.SCALE), (int)((ERASER_HEIGHT+7)*Game.SCALE));
        loadEnemyImgs();
    }

    private void updateAniTick(){
        aniTick ++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex ++;
            if (aniIndex >= GetSpriteAmount(enemyState)){
                aniIndex = 0;
                }
            }
        }

    public void updatePos() {
        Rectangle[] lvlDat = levelManager.getLvlData();

        // Horizontal movement
        if (!CanMoveHere((int) (hitbox.x + xSpeed), hitbox.y, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
            xSpeed = -xSpeed; // Reverse direction if hitting an obstacle
        } else {
            hitbox.x += xSpeed;
            x = hitbox.x;
        }

        // Vertical movement (gravity)
        if (!CanMoveHere(hitbox.x, hitbox.y + airSpeed, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
            airSpeed = 0; // Stop vertical movement if hitting the ground
        } else {
            hitbox.y += airSpeed;
            y = hitbox.y;
            airSpeed += gravity; // Apply gravity
        }
    }




    public void draw(Graphics g){
        g.drawImage(eraserArray[enemyState][aniIndex], (int)(this.getHitbox().x-xDrawOffset), (int)(this.getHitbox().y-yDrawOffset), ERASER_WIDTH, ERASER_HEIGHT, null);
        drawHitbox(g);
    }



    private void loadEnemyImgs() {
        eraserArray = new BufferedImage[2][7];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ENEMIES_ATLAS);
        for (int j = 0; j < eraserArray.length; j++){
            for (int i = 0; i < eraserArray[j].length; i++){
                eraserArray[j][i] = temp.getSubimage((i*ERASER_WIDTH_DEFAULT), j*ERASER_HEIGHT_DEFAULT, ERASER_WIDTH_DEFAULT, ERASER_HEIGHT_DEFAULT);
                System.out.print(i);
            }
        }
    }

    public void update(){
        updatePos();
        updateAniTick();
        updateHitbox();
    }

}
