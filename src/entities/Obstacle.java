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
    private float xPos;
    private float yPos;
    private int obbyAni;
    private Graphics g;
    private int GemCounter = 0;

    private LevelManager levelManager;



    public Obstacle(String type, float xPos, float yPos, Game game) {
        super(xPos, yPos, 75, 46, game);
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = type;
        levelManager = new LevelManager(game);
        loadAnimations();
        if (type.equals("GEM")) {
            innitHitBox(xPos + 90f, yPos +50f, 50 * Game.SCALE, 15 * Game.SCALE);
        }
        else if (type.equals("FIRE")){
            innitHitBox(xPos + 150f, yPos + 80f, 45 * Game.SCALE, 30 * Game.SCALE);
        }
        else if (type.equals("PENCIL")){
            innitHitBox(xPos + 150f, yPos + 90f, 80 * Game.SCALE, 100 * Game.SCALE);
        }

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
                animations[i][j] = img.getSubimage(105 * j, 75 * i, 105, 75);
            }
        }
    }

    public void render(Graphics g) {
        if (type.equals("GONE")) {
            g.drawImage(animations[GONE][aniIndex], (int) xPos, (int) yPos, 0, 0, null);}
        else if (type.equals("GEM")) {
            g.drawImage(animations[GEM][aniIndex], (int)xPos, (int)yPos, 75, 46, null);
        } else if (type.equals("FIRE")) {
            g.drawImage(animations[FIRE][aniIndex], (int)xPos, (int)yPos, 75, 46, null);
        } else if (type.equals("PENCIL")) {
            g.drawImage(animations[PENCIL][aniIndex], (int)xPos, (int)yPos, 95, 75, null);
        } else {
            g.drawImage(animations[GONE][aniIndex], (int)xPos, (int)yPos, 0, 0, null);
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





    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setX(int x){
        this.xPos = x;
    }
}








