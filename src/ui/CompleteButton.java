package ui;

import gamestates.Gamestate;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.*;

public class CompleteButton extends Buttons{
    private BufferedImage[] imgs;

    public CompleteButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        super(xPos, yPos, rowIndex, state);
        loadImages();
    }





    private void loadImages() {
        imgs = new BufferedImage[5];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.PAUSEBUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
        super.setImage(imgs);
    }
}
