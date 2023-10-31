package entities;

import main.Game;
import utilz.LoadSave;

import java.awt.image.BufferedImage;

public class Pencil extends Entity{
    private BufferedImage[] animations;

    public Pencil(float x, float y, int width, int height, Game game) {
        super(x, y, width, height, game);
    }

    public void loadAnimations(){
        BufferedImage img = LoadSave.GetSpriteAtlas("/pencil.png");
        animations = new BufferedImage[6];

        for (int i = 0; i < 6; i++){

        }
    }


}
