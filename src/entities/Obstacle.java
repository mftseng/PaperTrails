package entities;

import levels.LevelManager;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.ObstacleConstants.*;
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
        innitHitBox(xPos, yPos, 90 * Game.SCALE, 70 * Game.SCALE);

    }

    public void update() {
        updateAnimationTickOBBY();
        updateHitbox();
    }

    public void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.OBBY_ATLAS);
        animations = new BufferedImage[4][6];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(95 * j, 75 * i, 95, 75);
            }
        }
    }

    public void render(Graphics g) {
        if (obstacleCollision(game.getPlayer(1)) || obstacleCollision(game.getPlayer(2))) {
            CollisionResult(game.getPlayer(1));
            CollisionResult(game.getPlayer(2));
        } else if (type.equals("GEM")) {
            g.drawImage(animations[GEM][aniIndex], xPos, yPos, 75, 46, null);
        } else if (type.equals("FIRE")) {
            g.drawImage(animations[FIRE][aniIndex], xPos, yPos, 75, 46, null);
        } else if (type.equals("PENCIL")) {
            g.drawImage(animations[PENCIL][aniIndex], xPos, yPos, 95, 75, null);
        } else {
            g.drawImage(animations[GONE][aniIndex], xPos, yPos, 0, 0, null);
        }
        drawHitbox(g);
    }

    private void updateAnimationTickOBBY() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (type.equals("PENCIL")) {
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

    private boolean obstacleCollision(Player player) {
        if (player.getHitbox().x + player.getHitbox().width >= this.hitbox.x || player.getHitbox().x < this.hitbox.x + width
                || player.getHitbox().y < this.hitbox.y || player.getHitbox().y + player.getHitbox().height >= this.hitbox.y) {
            return true;
        } else {
            return false;
        }



    }

    private void CollisionResult(Player player) {
        if (obstacleCollision(player)) {
            if (type.equals("GEM"))
                g.drawImage(animations[GONE][aniIndex], xPos, yPos, 0, 0, null);
            else if (type.equals("FIRE") || type.equals("PENCIL"))
                player.setPlayerAction(GONE);
            else
                return;
        }
    }
}








