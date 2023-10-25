package levels;

import java.awt.*;
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
        drawLevelOne(g);
    }

    public void drawLevelOne(Graphics g){


        // bottom line
        g.fillRect(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*30));

        // bottom right stairs
        for (int i = 0; i <= 3; i ++){
            for (int j = 0; j <= i; j++){
                g.fillRect((Game.GAME_WIDTH- Game.BLOCK_SIZE - Game.BLOCK_SIZE * j), ((Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*4)+ Game.BLOCK_SIZE * i), Game.BLOCK_SIZE, Game.BLOCK_SIZE);

            }
        }
        //Line1
        g.fillRect(Game.BLOCK_SIZE*3, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2, Game.GAME_WIDTH - Game.BLOCK_SIZE*9, Game.LINE_SIZE);
        //Line2
        g.fillRect(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES, Game.GAME_WIDTH - Game.BLOCK_SIZE*9, Game.LINE_SIZE);
        //Line3
        g.fillRect(Game.GAME_WIDTH - Game.BLOCK_SIZE*6,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2,Game.LINE_SIZE,Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE);
        //Line 4
        g.fillRect(Game.BLOCK_SIZE*2,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2,Game.GAME_WIDTH - Game.BLOCK_SIZE*4,Game.LINE_SIZE);
        //Left side box
        g.fillRect(0,Game.FLOOR_HEIGHT - Game.SPACE_BETWEEN_LINES*2 - Game.BLOCK_SIZE*2,Game.SPACE_BETWEEN_LINES, Game.SPACE_BETWEEN_LINES);
        //Line5
        g.fillRect(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*3, Game.BLOCK_SIZE*2, Game.LINE_SIZE);
        //Line6
//        g.fillRect(Game.BLOCK_SIZE*9, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*3, Game.SPACE_BETWEEN_LINES*2, Game.LINE_SIZE);
        //Line7
        g.fillRect(Game.SPACE_BETWEEN_LINES*2,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE - Game.SPACE_BETWEEN_LINES*5,Game.BLOCK_SIZE*9,Game.BLOCK_SIZE/3);
        //Line8
        g.fillRect(Game.BLOCK_SIZE*13,Game.SPACE_BETWEEN_LINES,Game.BLOCK_SIZE*9,Game.BLOCK_SIZE/3);

        //Moving Gate 1
        g.fillRect(Game.GAME_WIDTH - Game.BLOCK_SIZE*6,(Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2)+(Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE),Game.LINE_SIZE,Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE);

        //Moving Gate 2
        g.fillRect(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*3, Game.BLOCK_SIZE*2, Game.SPACE_BETWEEN_LINES + Game.LINE_SIZE);




    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);

        levelSprite = new BufferedImage[5];
        levelSprite[0] = img.getSubimage(32,32,64,64);
        levelSprite[1] = img.getSubimage(32,32, 800,10);

    }




}


