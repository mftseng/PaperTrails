package levels;

import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import main.Game;
import utilz.LoadSave;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();

    }





    public void draw(Graphics g) {
//        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
//            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
//                int index = levelOne.getSpriteIndex(i, j);
//                g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
//            }
//        g.drawLine(0, 0, 100, 100);

    }

    public void update() {

    }





    public void render(Graphics g) {
        g.drawImage(levelSprite[0],0,40,null);
        g.drawImage(levelSprite[1],0,400,null);
    }

    public void drawLevelOne(){

    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);

        levelSprite = new BufferedImage[5];
        levelSprite[0] = img.getSubimage(32,32,64,64);
        levelSprite[1] = img.getSubimage(32,32,1000,10);

    }




}


