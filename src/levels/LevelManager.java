package levels;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import entities.Obstacle;
import main.Game;
import org.w3c.dom.css.Rect;
import utilz.LoadSave;

import static levels.Level.*;
import static utilz.Constants.PlayerConstants.GetSpriteAmount;
import static utilz.HelpMethods.CanMoveHere;

public class LevelManager {
    private int aniTick, aniIndex, aniSpeed = 17;
    private int playerAction;
    private Game game;
    private BufferedImage[] animations;
    private int levelNum = 0;
    private static Rectangle[] lvlDat;
    private int levelChange = 1;
    private boolean LevelCreated = false;
    private static boolean isThereButtons;
    private static Rectangle[] buttons;
    private static Obstacle[] obstacles;




    public LevelManager(Game game) {
        this.game = game;
        loadAnimations();

    }



    public void update() {

    }

    public int getLevelNum(){
        return levelNum;
    }
    public boolean getisThereButtons(){return isThereButtons;}
    public Rectangle[] getLvlData(){return lvlDat;}
    public Rectangle[] getButtons(){return buttons;}
    public Obstacle[] getObstacles(){return obstacles;}


    public void updateLevel(){

    }


    public void render(Graphics g) {
        createLevel(g);
        drawLevel(g);
//        System.out.println(obstacles.length);
    }

    public void createLevel1(Graphics g){
        lvlDat = new Rectangle[1];
        lvlDat[0] = new Rectangle(0, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2,Game.GAME_WIDTH,Game.GAME_HEIGHT/2);
        g.drawImage(animations[aniIndex], Game.BLOCK_SIZE*3, Game.BLOCK_SIZE, 700, 200, null);

        obstacles = new Obstacle[2];
        obstacles[0] = new Obstacle("PENCIL", 400, 400, game);
        obstacles[1] = new Obstacle("GEM", 270, 400, game);
    }

    public void createLevel2(){
        lvlDat = new Rectangle[4];
        lvlDat[0] = new Rectangle(0, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2,Game.GAME_WIDTH,Game.GAME_HEIGHT/2);
        for (int i = 1; i < 4; i++) {
            lvlDat[i] = new Rectangle(Game.BLOCK_SIZE*2 + Game.BLOCK_SIZE*3*i, Game.GAME_HEIGHT / 2 + Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
        }
    }

    public void createLevel3(){
        lvlDat = new Rectangle[8];
        //Floor line
        lvlDat[0] = new Rectangle(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*30));
        //Bottom Rectangle
        lvlDat[1] = new Rectangle(Game.BLOCK_SIZE*4, Game.GAME_HEIGHT - Game.BLOCK_SIZE - Game.LINE_SIZE*4, Game.BLOCK_SIZE*8, Game.JUMP_HEIGHT+ Game.BLOCK_SIZE);
        //Left bottom line
        lvlDat[2] = new Rectangle(0, Game.FLOOR_HEIGHT- Game.JUMP_HEIGHT*2 - Game.LINE_SIZE , Game.BLOCK_SIZE*4, Game.LINE_SIZE);
        //Right bottom line
        lvlDat[3] = new Rectangle(Game.BLOCK_SIZE*12, Game.FLOOR_HEIGHT- Game.JUMP_HEIGHT*2 - Game.LINE_SIZE , Game.BLOCK_SIZE*4, Game.LINE_SIZE);
        //Middle middle line
        lvlDat[4] = new Rectangle(Game.BLOCK_SIZE*4, Game.GAME_HEIGHT - Game.BLOCK_SIZE - Game.LINE_SIZE*5 - Game.JUMP_HEIGHT*2, Game.BLOCK_SIZE*8, Game.LINE_SIZE);
        //Left middle line
        lvlDat[5] = new Rectangle(0, Game.FLOOR_HEIGHT- Game.JUMP_HEIGHT*4 - Game.LINE_SIZE*2, Game.BLOCK_SIZE*4, Game.LINE_SIZE);
        //Right middle line
        lvlDat[6] = new Rectangle(Game.BLOCK_SIZE*12, Game.FLOOR_HEIGHT- Game.JUMP_HEIGHT*4- Game.LINE_SIZE*2, Game.BLOCK_SIZE*4, Game.LINE_SIZE);
        //Middle top line
        lvlDat[7] = new Rectangle(Game.BLOCK_SIZE*4, Game.GAME_HEIGHT - Game.BLOCK_SIZE - Game.LINE_SIZE*6 - Game.JUMP_HEIGHT*4, Game.BLOCK_SIZE*8, Game.LINE_SIZE);


    }

    public void createLevel4(){
        isThereButtons = true;
        lvlDat = new Rectangle[24];
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

        // bottom line
        lvlDat[8] = new Rectangle(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*30));

        // bottom right stairs
        int index = 9;
        for (int i = 0; i <= 3; i ++){
            for (int j = 0; j <= i; j++){
                lvlDat[index] = new Rectangle(Game.GAME_WIDTH- Game.BLOCK_SIZE - Game.BLOCK_SIZE * j, ((Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*4)+ Game.BLOCK_SIZE * i), Game.BLOCK_SIZE, Game.BLOCK_SIZE);
                index ++;
            }
        }

        //bottom left line
        lvlDat[19] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE, Game.BLOCK_SIZE*2, Game.LINE_SIZE);

        //Moving Gate 1
        lvlDat[20] = new Rectangle(Game.GAME_WIDTH - Game.BLOCK_SIZE*6,(Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2)+(Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE),Game.LINE_SIZE,Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE);
        //Moving Gate 2
        lvlDat[21] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*3, Game.BLOCK_SIZE*2, Game.SPACE_BETWEEN_LINES + Game.LINE_SIZE);

        //button for Moving Gate 1
        lvlDat[22] = new Rectangle(Game.BLOCK_SIZE*9,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.BUTTON_HEIGHT,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        //button for Moving Gate 2
        lvlDat[23] = new Rectangle(Game.BLOCK_SIZE*6,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*4 - Game.BUTTON_HEIGHT- Game.LINE_SIZE*2,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);

        //buttons
        buttons = new Rectangle[2];
        buttons[0] = new Rectangle(Game.BLOCK_SIZE*9,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.BUTTON_HEIGHT,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        buttons[1] = new Rectangle(Game.BLOCK_SIZE*6,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*4 - Game.BUTTON_HEIGHT- Game.LINE_SIZE*2,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);


        LevelCreated = true;
    }


    public void drawLevel(Graphics g){
        //print function
//        System.out.println(isThereButtons);
        for (int i = 0; i < lvlDat.length; i++) {
            g.fillRect(lvlDat[i].x, lvlDat[i].y, lvlDat[i].width, lvlDat[i].height);
        }
        if (isThereButtons){
            for (int j = 0; j < buttons.length; j++){
            g.setColor(Color.PINK);
            g.fillRect(buttons[j].x, buttons[j].y, buttons[j].width, buttons[j].height);
        }

    }
    }




    public void createLevel(Graphics g){
        switch(1){
            case 1:
                if(!LevelCreated){
                    createLevel1(g);
                }

                break;
            case 2:
                if(!LevelCreated){
                    createLevel2();
                }
                break;
            case 3:
                if(!LevelCreated){
                    createLevel3();
                }
                break;
            case 4:
                if(!LevelCreated){
                    createLevel4();
                }
                break;
            case 5:
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



    public void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.TITLE_ATLAS);
        animations = new BufferedImage[3];
        animations[0] = img.getSubimage( 0, 166, 175, 53);
        animations[1] = img.getSubimage( 175, 166, 195, 53);
        animations[2] = img.getSubimage(370, 166, 210, 53);


    }
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 3) {
                aniIndex = 0;
            }
        }
    }
//    public Obstacle[] getObstacles{
//        return obstacles;
//    }

    public boolean getLevelCreated(){
        return LevelCreated;
    }





}


