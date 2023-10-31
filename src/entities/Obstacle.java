package entities;

import levels.LevelManager;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.PlayerConstants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends Entity {
    private BufferedImage[][] animations;
    private String type;
    private int aniIndex, aniTick, aniSpeed = 17;
    private int xPos;
    private int yPos;
    private int obbyAni;
    private Graphics g;



    public Obstacle(String type, int xPos, int yPos, Game game) {
        super(xPos, yPos, 75, 46, game);
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = type;
        loadAnimations();
        innitHitBox(xPos, yPos, 70 * Game.SCALE, 40 * Game.SCALE);

    }

    public void update() {
        updateHitbox();
        updateAnimationTick();
    }

    public void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.OBBY_ATLAS);
        animations = new BufferedImage[2][6];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(29, 115, 75, 46);
            }
        }
    }

    public void render(Graphics g) {
        if (type == "PENCIL") {
            g.drawImage(animations[obbyAni][aniIndex], xPos, yPos, 75, 46, null);
        } else if (type == "FIRE") {
            g.drawImage(animations[obbyAni][aniIndex], xPos, yPos, 75, 46, null);
        } else {
            g.drawImage(animations[obbyAni][aniIndex], xPos, yPos, 75, 46, null);
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (type == "PENCIL") {
                if (aniIndex >= 6) {
                    aniIndex = 0;
                }
            } else {
                if (aniIndex >= 2) {
                    aniIndex = 0;
                }
            }
        }
    }

    private boolean obstacleCollision(Player player){
       if (player.getHitbox().x + player.getHitbox().width >= this.hitbox.x || player.getHitbox().x < this.hitbox.x + width
       || player.getHitbox().y < this.hitbox.y || player.getHitbox().y + player.getHitbox().height >= this.hitbox.y){
           return true;
       }
       else{ return false;}


    }


    private void CollisionResult(Player player){
        if (obstacleCollision(player)){
            if (this.type == "GEM")
                this.obbyAni = DISAPPEAR;
            else
                player.setPlayerAction(DISAPPEAR);
            }
        }

    }


