package entities;

import main.Game;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x, y;
    protected Rectangle2D.Float hitbox;
    protected Game game;
    protected int width, height;


    public Entity(float x, float y, int width, int height, Game game) {
        this.x = x ;
        this.y = y ;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    protected void drawHitbox(Graphics g) {
        //for debugging the hitbox
        g.setColor(Color.PINK);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    protected void innitHitBox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    protected void setHitbox(float x, float y){
        hitbox.x = x;
        hitbox.y=y;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

}








