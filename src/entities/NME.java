package entities;

import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.EnemyConstants.ERASER_HEIGHT;
import static utilz.Constants.EnemyConstants.ERASER_WIDTH;
import static utilz.Constants.ObstacleConstants.ERASER;
import static utilz.Constants.ObstacleConstants.FIRE;
import static utilz.HelpMethods.CanMoveHere;


public class NME extends Obstacle{
    private boolean inAir;
    private float airSpeed = 0f;
    private float gravity = .15f * Game.SCALE;
    private float xSpeed;
    public NME(float xPos, float yPos, Game game, float xspeed) {
        super(xPos, yPos, game);
        innitHitBox(x, y , (int)((ERASER_WIDTH-40)*Game.SCALE), (int)((ERASER_HEIGHT)*Game.SCALE));
        this.xSpeed = xspeed;
        levelManager = new LevelManager(game);
    }

    @Override
    public void render(Graphics g) {
        BufferedImage currentImage = animations[ERASER][aniIndex];

        // Flip the image horizontally if moving left
        if (xSpeed < 0) {
            // Create a horizontally flipped image
            currentImage = flipImage(currentImage);
        }

        g.drawImage(currentImage, (int) (x - 35), (int) (y - 15), ERASER_WIDTH, ERASER_HEIGHT, null);
        drawHitbox(g);
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
    @Override
    public void update(){
        updatePos();
        updateHitbox();
        updateAnimationTickOBBY();
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

    private BufferedImage flipImage(BufferedImage image) {
        BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Create a graphics object from the flipped image
        Graphics2D g = flippedImage.createGraphics();

        // Flip the image horizontally using transformation
        g.drawImage(image, image.getWidth(), 0, 0, image.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);

        // Dispose the graphics object
        g.dispose();

        return flippedImage;
    }


}
