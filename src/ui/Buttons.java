package ui;

import gamestates.Gamestate;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.*;

public abstract class Buttons {
    protected int xPos, yPos, rowIndex, aniTick, aniSpeed = 60;
    protected Gamestate state;
    protected int index;
    BufferedImage[] imgs;
    protected int xOffsetCenter = B_WIDTH/2;
    protected boolean mouseOver, mousePressed;
    protected Rectangle bounds;

    public Buttons(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }



    public void draw(Graphics g){
        g.drawImage(imgs[index],  xPos - xOffsetCenter, yPos, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT, null);
    }

    public void update(){
        aniTick++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            index ++;
            if (index > 1){
                index = 0;
            }
        }

        else if(mouseOver) {
            index = 2;
            while (index < imgs.length - 1) {
                index++;
            }
        }

        else if(mousePressed){
            index = 1;


        }
    }


    public boolean isMouseOver(){
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void applyGamestate(){
        Gamestate.state = state;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = true;
    }

    public void setImage(BufferedImage[] imgs){
        this.imgs = imgs;

    }

    public Gamestate getState(){
        return this.state;
    }

    public void setState(Gamestate state){
        this.state = state;
    }
}


