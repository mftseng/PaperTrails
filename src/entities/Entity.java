package entities;

import main.Game;
import org.w3c.dom.css.Rect;

import java.awt.*;

public abstract class Entity {

    protected float x, y;
    protected Rectangle hitbox;
    protected Game game;

    public Entity(float x, float y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        innitHitBox();
    }

    protected void drawHitbox(Graphics g) {
        //for debugging the hitbox
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x, hitbox.y, Game.CHAR_WIDTH, Game.CHAR_HEIGHT);
    }

    private void innitHitBox() {
        hitbox = new Rectangle((int) x, (int) y);
    }

    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

}








