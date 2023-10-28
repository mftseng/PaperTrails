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
import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.getBottomOverlap;

public class Player extends Entity {

    private int aniTick, aniIndex, aniSpeed = 17;
    private final int playerNum;
    private int playerAction;
    private boolean moving = false;
    private BufferedImage[][] animations;
    private boolean left, right, up, down;
    private float playerSpeed = 2.0f;
    private LevelManager levelManager;

    private float xDrawOffset = 60 * Game.SCALE;
    private float yDrawOffset = 30 * Game.SCALE;


    public Player(float x, float y, int playerNum, Game game) {
        super(x, y, Game.CHAR_WIDTH, Game.CHAR_HEIGHT, game);
        this.playerNum = playerNum;
        loadAnimations();
        levelManager = new LevelManager(game);
        innitHitBox(x, y, 58 * Game.SCALE, 95 * Game.SCALE);
    }

    public void choosePlayerMode() {
        if (this.playerNum == 1) {
            this.playerAction = IDLE1;
        } else {
            this.playerAction = IDLE2;
        }
    }

    public void update() {
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        if (this.playerNum == 1)
            g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), Game.CHAR1_WIDTH, Game.CHAR1_HEIGHT, null);
        else
            g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), Game.CHAR2_WIDTH, Game.CHAR2_HEIGHT, null);
//        drawHitbox(g);

    }

    public void setDirection(int direction) {

    }


    public void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[7][7];

        for (int i = 0; i < animations.length; i++)
            for (int j = 0; j < animations[i].length; j++)
                animations[i][j] = img.getSubimage(j * 160, i * 160, 120, 130);
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
        if(moving)
            if (playerNum == 1)
                playerAction = RUNNING1;
            else
                playerAction = RUNNING2;
        else
            if (playerNum == 1)
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
        if (!left && !right && !up && !down)
            return;

        float xSpeed = 0, ySpeed = 0;

        if (left && !right)
            xSpeed = -playerSpeed;
        else if (right && !left)
            xSpeed = playerSpeed;
        if (up && !down)
            ySpeed = -playerSpeed;
        else if (down && !up)
            ySpeed = playerSpeed;

        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            x = hitbox.x;
            y = hitbox.y;
            moving = true;
        }

        System.out.println(getBottomOverlap());
       // System.out.println(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, (int) hitbox.width, (int) hitbox.height, lvlDat));
    }
}




