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
    private int levelNum = 0;
    private final static Rectangle[][] lvlDat = new Rectangle[9][21];

    public LevelManager(Game game) {
        this.game = game;

    }



    public void update() {

    }

    public int getLevelNum(){
        return levelNum;
    }

    public void updateLevel(){

    }


    public void render(Graphics g) {
        createAllLevels();
        drawLevel(g);
    }

    public void createAllLevels(){
        //Line1
        lvlDat[0][0] = new Rectangle(Game.BLOCK_SIZE*3, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2, Game.GAME_WIDTH - Game.BLOCK_SIZE*9, Game.LINE_SIZE);
        //Line2
        lvlDat[0][1] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES, Game.GAME_WIDTH - Game.BLOCK_SIZE*9, Game.LINE_SIZE);
        //Line3
        lvlDat[0][2] = new Rectangle(Game.GAME_WIDTH - Game.BLOCK_SIZE*6,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2,Game.LINE_SIZE,Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE);
        //Line 4
        lvlDat[0][3] = new Rectangle (Game.BLOCK_SIZE*2,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2,Game.GAME_WIDTH - Game.BLOCK_SIZE*4,Game.LINE_SIZE);
        //Line5
        lvlDat[0][4] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*3, Game.BLOCK_SIZE*2, Game.LINE_SIZE);
        //Line6
        lvlDat[0][5] = new Rectangle(Game.SPACE_BETWEEN_LINES*2,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE - Game.SPACE_BETWEEN_LINES*5,Game.BLOCK_SIZE*9,Game.BLOCK_SIZE/3);
        //Line 7
        lvlDat[0][6] = new Rectangle(Game.BLOCK_SIZE*13,Game.SPACE_BETWEEN_LINES,Game.BLOCK_SIZE*9,Game.BLOCK_SIZE/3);

        //Left side box
        lvlDat[0][7] = new Rectangle (0,Game.FLOOR_HEIGHT - Game.SPACE_BETWEEN_LINES*2 - Game.BLOCK_SIZE*2,Game.SPACE_BETWEEN_LINES, Game.SPACE_BETWEEN_LINES);

        //Moving Gate 1
        lvlDat[0][8] = new Rectangle(Game.GAME_WIDTH - Game.BLOCK_SIZE*6,(Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2)+(Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE),Game.LINE_SIZE,Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE);

        //Moving Gate 2
        lvlDat[0][9] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*3, Game.BLOCK_SIZE*2, Game.SPACE_BETWEEN_LINES + Game.LINE_SIZE);

        // bottom line
        lvlDat[0][10] = new Rectangle(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*30));

        // bottom right stairs
        int index = 11;
        for (int i = 0; i <= 3; i ++){
            for (int j = 0; j <= i; j++){
                lvlDat[0][index] = new Rectangle(Game.GAME_WIDTH- Game.BLOCK_SIZE - Game.BLOCK_SIZE * j, ((Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*4)+ Game.BLOCK_SIZE * i), Game.BLOCK_SIZE, Game.BLOCK_SIZE);
                index ++;
            }
        }

    }

    public Rectangle[] getLvlData(){
        return lvlDat[levelNum];
    }

    public void drawLevel(Graphics g){
        //print function
        for (int i = 0; i < lvlDat[levelNum].length; i++){
            g.fillRect(lvlDat[levelNum][i].x,lvlDat[levelNum][i].y,lvlDat[levelNum][i].width,lvlDat[levelNum][i].height);
        }
    }





}


