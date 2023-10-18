package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity{
    private int aniTick, aniIndex, aniSpeed = 17;
    private int playerAction = IDLE1;
    private boolean moving = false;
    private BufferedImage[][] animations;
    private boolean left, right, up, down;
    private float playerSpeed = 2.0f;



    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 120, 130, null);

    }
    public void setDirection(int direction){

    }

    private void updatePos() {
        moving = false;

        if (left && !right){
            x -= playerSpeed;
            moving = true;
        }
        else if (right && !left){
            x += playerSpeed;
            moving = true;
        }

        if (up && !down){
            y -= playerSpeed;
            moving = true;
        }
        else if (down && !up){
            y += playerSpeed;
            moving = true;
        }
    }

    private void setAnimation() {
        if(moving)
            playerAction = RUNNING1;
        else
            playerAction = IDLE1;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }
    public void loadAnimations(){
        InputStream is = getClass().getResourceAsStream("/Stick_figs.png");

        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[7][7];

            for(int i = 0; i < animations.length; i++)
                for (int j = 0; j < animations[i].length; j++)
                    animations[i][j] = img.getSubimage(j * 160, i * 160, 120, 130);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
    }
}

    public void resetDirBooleans(){
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
}