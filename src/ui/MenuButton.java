package ui;

import gamestates.Gamestate;
import utilz.LoadSave;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

import static utilz.Constants.UI.*;

public class MenuButton {
    private int xPos, yPos, rowIndex;
    private Gamestate state;
    private BufferedImage[] imgs;
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImages();
    }

   private void loadImages() {
       imgs = new BufferedImage[2];
       BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
       for (int i = 0; i < imgs.length; i++){
           temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex*B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT,B_HEIGHT_DEFAULT);
       }
   }
}
