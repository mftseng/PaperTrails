package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER_ATLAS = "Stick_figs.png";
    public static final String TITLE_ATLAS = "Obstacles.png";
    public static final String OBBY_ATLAS = "Obstacles_Sprites.png";
    public static final String MENU_BUTTONS = "menuButtons.png";
    public static final String MENU_TITLE = "menuTitle.png";
    public static final String BINDER_PAPER = "binder_paper2.png";
    public static final String ENEMIES_ATLAS = "Eraser_and_P1_Idle&Walk.png";
    public static final String COMPLETE_TITLE = "LevelCompleteScreen.png";
    public static final String GRADES = "Grades.png";
    public static final String PAUSEBUTTONS = "pausebuttons.png";


    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }



}