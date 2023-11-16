package entities;

import levels.LevelManager;
import main.Game;
import utilz.LoadSave;

import static utilz.Constants.EnemyConstants.ERASER_HEIGHT;
import static utilz.Constants.EnemyConstants.ERASER_WIDTH;
import static utilz.Constants.ObstacleConstants.*;
import static utilz.Constants.PlayerConstants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Obstacle extends Entity {
    protected BufferedImage[][] animations;

    protected int aniIndex, aniTick, aniSpeed = 17;
    protected float xPos;
    protected float yPos;
    protected LevelManager levelManager;


    public Obstacle(float xPos, float yPos, Game game) {
        super(xPos, yPos, 70, 65, game);
        this.xPos = xPos;
        this.yPos = yPos;
        levelManager = new LevelManager(game);
        loadAnimations();

    }

    public void update() {
        updateAnimationTickOBBY();
        updateHitbox();
    }

    public void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.OBBY_ATLAS);
        animations = new BufferedImage[5][7];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(75 * j, 70 * i, 75, 70);
            }
        }
    }

    public void render(Graphics g) {
    }

    protected void updateAnimationTickOBBY() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 2) {
                aniIndex = 0;
            }
        }
    }
}











