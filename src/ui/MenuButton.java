package ui;

import gamestates.Gamestate;
import org.w3c.dom.css.Rect;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import static utilz.Constants.UI.*;

public class MenuButton extends Buttons {
    private BufferedImage[] imgs;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        super(xPos, yPos, rowIndex, state);
        loadImages();
    }


//    private void initBounds() {
//        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
//    }


    private void loadImages() {
        imgs = new BufferedImage[5];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
        super.setImage(imgs);
    }
}
//
//    public void draw(Graphics g){
//        g.drawImage(imgs[index],  xPos - xOffsetCenter, yPos, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT, null);
//    }
//
//    public void update(){
//        aniTick++;
//        if (aniTick >= aniSpeed){
//            aniTick = 0;
//            index ++;
//            if (index > 1){
//                index = 0;
//            }
//        }
//
//        else if(mouseOver) {
//            index = 2;
//            while (index < imgs.length - 1) {
//                index++;
//            }
//        }
//
//        else if(mousePressed){
//            index = 1;
//
//
//        }
//    }
//
//
//    public boolean isMouseOver(){
//        return mouseOver;
//    }
//
//    public void setMouseOver(boolean mouseOver){
//        this.mouseOver = mouseOver;
//    }
//
//    public boolean isMousePressed() {
//        return mousePressed;
//    }
//
//    public void setMousePressed(boolean mousePressed){
//        this.mousePressed = mousePressed;
//    }
//
//    public Rectangle getBounds(){
//        return bounds;
//    }
//
//    public void applyGamestate(){
//        Gamestate.state = state;
//    }
//
//    public void resetBools(){
//        mouseOver = false;
//        mousePressed = true;
//    }
//}
