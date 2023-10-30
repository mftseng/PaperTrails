package levels;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import main.Game;
import utilz.LoadSave;

import static levels.Level.*;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private BufferedImage[][] title;
    private Level levelOne;
    private int levelNum = 0;
    private static Rectangle[] lvlDat;
    private int levelChange = 1;

    private boolean LevelCreated = false;

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
        createLevel();
        drawLevel(g);
    }

    public void createLevel1(){
        lvlDat = new Rectangle[1];
        lvlDat[0] = new Rectangle(0, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2,Game.GAME_WIDTH,Game.GAME_HEIGHT/2);
    }

    public void createLevel2(){
        lvlDat = new Rectangle[4];
        lvlDat[0] = new Rectangle(0, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2,Game.GAME_WIDTH,Game.GAME_HEIGHT/2);
        for (int i = 1; i < 4; i++) {
            lvlDat[i] = new Rectangle(Game.BLOCK_SIZE*2 + Game.BLOCK_SIZE*3*i, Game.GAME_HEIGHT / 2 + Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
        }
    }

    public void createLevel3(){
        lvlDat = new Rectangle[];

    }

    public void createLevel5(){
        lvlDat = new Rectangle[21];
        //Line1
        lvlDat[0] = new Rectangle(Game.BLOCK_SIZE*3, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2, Game.GAME_WIDTH - Game.BLOCK_SIZE*9, Game.LINE_SIZE);
        //Line2
        lvlDat[1] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES, Game.GAME_WIDTH - Game.BLOCK_SIZE*9, Game.LINE_SIZE);
        //Line3
        lvlDat[2] = new Rectangle(Game.GAME_WIDTH - Game.BLOCK_SIZE*6,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2,Game.LINE_SIZE,Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE);
        //Line 4
        lvlDat[3] = new Rectangle (Game.BLOCK_SIZE*2,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2,Game.GAME_WIDTH - Game.BLOCK_SIZE*4,Game.LINE_SIZE);
        //Line5
        lvlDat[4] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*3, Game.BLOCK_SIZE*2, Game.LINE_SIZE);
        //Line6
        lvlDat[5] = new Rectangle(Game.SPACE_BETWEEN_LINES*2,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE - Game.SPACE_BETWEEN_LINES*5,Game.BLOCK_SIZE*9,Game.BLOCK_SIZE/3);
        //Line 7
        lvlDat[6] = new Rectangle(Game.BLOCK_SIZE*13,Game.SPACE_BETWEEN_LINES + Game.LINE_SIZE *3,Game.BLOCK_SIZE*9,Game.BLOCK_SIZE/3);

        //Left side box
        lvlDat[7] = new Rectangle (0,Game.FLOOR_HEIGHT - Game.SPACE_BETWEEN_LINES*2 - Game.BLOCK_SIZE*2,Game.SPACE_BETWEEN_LINES, Game.SPACE_BETWEEN_LINES);

        //Moving Gate 1
        lvlDat[8] = new Rectangle(Game.GAME_WIDTH - Game.BLOCK_SIZE*6,(Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2)+(Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE),Game.LINE_SIZE,Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE);

        //Moving Gate 2
        lvlDat[9] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*3, Game.BLOCK_SIZE*2, Game.SPACE_BETWEEN_LINES + Game.LINE_SIZE);

        // bottom line
        lvlDat[10] = new Rectangle(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*30));

        // bottom right stairs
        int index = 11;
        for (int i = 0; i <= 3; i ++){
            for (int j = 0; j <= i; j++){
                lvlDat[index] = new Rectangle(Game.GAME_WIDTH- Game.BLOCK_SIZE - Game.BLOCK_SIZE * j, ((Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*4)+ Game.BLOCK_SIZE * i), Game.BLOCK_SIZE, Game.BLOCK_SIZE);
                index ++;
            }
        }
        LevelCreated = true;
    }


    public Rectangle[] getLvlData(){
        return lvlDat;
    }

    public void drawLevel(Graphics g){
        //print function
        for (int i = 0; i < lvlDat.length; i++){
            g.fillRect(lvlDat[i].x,lvlDat[i].y,lvlDat[i].width,lvlDat[i].height);
        }
    }


    public void createLevel(){
        switch(levelChange){
            case 1:
                if(!LevelCreated){
                    createLevel1();
                }
                break;
            case 2:
                if(!LevelCreated){
                    createLevel2();
                }
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                if(!LevelCreated){
                    createLevel5();
                }
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:   // ending
                levelChange++;
                 break;
            default:
                break;


        }
    }





}


