package ui;

import gamestates.Gamestate;
import utilz.LoadSave;

import java.awt.image.BufferedImage;

import static utilz.Constants.UI.B_HEIGHT_DEFAULT;
import static utilz.Constants.UI.B_WIDTH_DEFAULT;

public class CompleteButtons extends Buttons{
    private BufferedImage[] imgs;

    public CompleteButtons(int xPos, int yPos, int rowIndex, Gamestate state) {
        super(xPos, yPos, rowIndex, state);
        loadImages();
    }


//    private void initBounds() {
//        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
//    }


    private void loadImages() {
        imgs = new BufferedImage[5];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.PAUSEBUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
        super.setImage(imgs);
    }
}
