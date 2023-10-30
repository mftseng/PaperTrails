package entities;

import levels.Level;
import levels.LevelManager;
import main.Game;
import main.GamePanel;
import utilz.HelpMethods;
import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;
import static levels.LevelManager.*;
import static utilz.HelpMethods.*;

public class Player extends Entity {

    private int aniTick, aniIndex, aniSpeed = 17;
    private final int playerNum;
    private int playerAction;
    private boolean moving = false;
    private boolean movingX = false;
    private BufferedImage[][] animations;
    private boolean left, right, up, down;
    private float playerSpeed = 2.0f;
    private LevelManager levelManager;

    private float xDrawOffset = 60 * Game.SCALE;
    private float yDrawOffset = 30 * Game.SCALE;

    //Jumping.Gravity
    private float airSpeed = 0f;
    private float gravity = .15f * Game.SCALE;
    private float jumpSpeed = -6.1f * Game.SCALE;
    private boolean inAir = false;



    public Player(float x, float y, int playerNum, Game game) {
        super(x, y, Game.CHAR_WIDTH, Game.CHAR_HEIGHT, game);
        this.playerNum = playerNum;
        loadAnimations();
        levelManager = new LevelManager(game);
        innitHitBox(x, y, 58 * Game.SCALE, 95 * Game.SCALE);


    }


    public void update() {
        //isInAir();
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();

        System.out.println("inAir: " + inAir);
        System.out.println("Moving: " + moving);
        System.out.println("MovingX: " + movingX);
        System.out.println();
    }


    public void render(Graphics g) {
        if (this.playerNum == 1)
            g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), Game.CHAR1_WIDTH, Game.CHAR1_HEIGHT, null);
        else
            g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), Game.CHAR2_WIDTH, Game.CHAR2_HEIGHT, null);
        drawHitbox(g);

    }

    public void setDirection(int direction) {

    }


    public void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[9][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 160, i * 160, 120, 130);
            }
        }
        animations[7][0] = animations[4][4];
        animations[8][0] = animations[5][2];


    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if(movingX) {
            if (playerNum == 1) {
                playerAction = RUNNING1;
            } else {
                playerAction = RUNNING2;
            }
        }
        else if (!movingX && moving){
            if (playerNum == 1) {
                playerAction = JUMPING1;
            } else {
                playerAction = JUMPING2;
            }
        }
        else if (playerNum == 1)
            playerAction = IDLE1;
        else
            playerAction = IDLE2;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public int getAniTick() {
        return aniTick;
    }

    public void setAniTick(int aniTick) {
        this.aniTick = aniTick;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    private void updatePos() {
        Rectangle[] lvlDat = levelManager.getLvlData(); // Access the lvlDat array
        moving = false;
        movingX = false;

        float xSpeed = 0;
        if (!left && !right && !up && !inAir) {
            return;
        }
        inAir = CanMoveHere(hitbox.x, hitbox.y + airSpeed, (int) hitbox.width, (int) hitbox.height, lvlDat);



        if (left)
            xSpeed -= playerSpeed;
        if (right)
            xSpeed += playerSpeed;


        if (up) {
            if (!inAir) {
                airSpeed = jumpSpeed;
                inAir = true;
            }

        }
        if (inAir) {
            if(CanMoveHere(hitbox.x, hitbox.y + airSpeed,(int) hitbox.width, (int) hitbox.height, lvlDat)){
                hitbox.y += airSpeed;
                y = hitbox.y;
                airSpeed += gravity;
                if(right || left)
                    updateXPos(xSpeed);
            }
        } else {
            airSpeed = 0;
            if(right || left)
                updateXPos(xSpeed);

        }

        moving = true;
//        System.out.println("inAir: " + inAir);
//        System.out.println("Moving: " + moving);
//        System.out.println("MovingX: " + movingX);
//        System.out.println();

//        System.out.println("Bottom Overlap: " + getBottomOverlap());
//        System.out.println("Top overlap: " + getTopOverlap());
//        System.out.println("Right overlap: " + getRightOverlap());
//        System.out.println("Bottom overlap: " + getBottomOverlap());

    }



    private void updateXPos(float xSpeed) {
        Rectangle[] lvlDat = levelManager.getLvlData(); // Access the lvlDat array
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
            hitbox.x += xSpeed;
            x = hitbox.x;
            movingX = true;
        }

    }

}




