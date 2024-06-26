/* Defines functions for creating and changing levels */
package levels;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.*;
import main.Game;
import utilz.LoadSave;

import static utilz.Constants.EnemyConstants.IDLE;
import static utilz.Constants.EnemyConstants.WALKING;

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
    private static Eraser[] enemies;
    Font gemCounterFont;
    private int gemCounter = 0;
    private static int level = 6;
    private boolean flag = false;
    private int buttonCount;




    /* LevelManager: creates a new LevelManager object with level set to 1. */
    public LevelManager(Game game) {
        this.game = game;
        loadAnimations();
        level = 1;

    }



    public void update() {

    }

    /* getters: */
    public int getLevelNum(){return levelNum;}
    public boolean getisThereButtons(){return isThereButtons;}
    public Rectangle[] getLvlData(){return lvlDat;}
    public Rectangle[] getButtons(){return buttons;}
    public Obstacle[] getObstacles(){return obstacles;}
    public Eraser[] getEnemies() {return enemies;}
    public boolean getAreThereObstacles(){return (obstacles.length > 0);}
    public boolean getLevelCreated(){return LevelCreated;}

    /* updateLevel: changes to next level by incrementing. */
    public static void updateLevel(){
        level++;
    }


    /* render: draws elements of current level onto screen. */
    public void render(Graphics g) {
        createLevel(g);
        drawLevel(g);
        g.setFont(gemCounterFont);
        g.setColor(Color.BLACK);
        g.drawString("" + gemCounter, Game.GAME_WIDTH/2, 200);
        updateAnimationTick();
//        System.out.println(obstacles.length);
    }

    /* level creation functions: */
    public void createLevel1(Graphics g){
        System.out.println(level);
        lvlDat = new Rectangle[1];
        lvlDat[0] = new Rectangle(0, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2,Game.GAME_WIDTH,Game.GAME_HEIGHT/2);

        obstacles = new Obstacle[4];
        obstacles[0] = new Fire( 900, 400, game);
        obstacles[1] = new Gem( 270, 400, game);
        obstacles[2] = new Pencil( 570, 430, game);
        obstacles[3] = new NME( 700, 400, game, 2f);


        LevelCreated = true;
    }

    public void createLevel2(){
        lvlDat = new Rectangle[4];
        lvlDat[0] = new Rectangle(0, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2,Game.GAME_WIDTH,Game.GAME_HEIGHT/2);
        for (int i = 1; i < 4; i++) {
            lvlDat[i] = new Rectangle(Game.BLOCK_SIZE*2 + Game.BLOCK_SIZE*3*i, Game.GAME_HEIGHT / 2 + Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
        }
        obstacles = new Obstacle[1];
        obstacles[0] = new Pencil( (float) Game.GAME_WIDTH - Game.BLOCK_SIZE*2, (float) Game.GAME_HEIGHT /2 - Game.BLOCK_SIZE, game);
//        obstacles[1] = new Pencil( Game.GAME_WIDTH - Game.BLOCK_SIZE*3, Game.GAME_HEIGHT/2 - Game.BLOCK_SIZE, game);

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
        ArrayList<Game_Button> GameButton = new ArrayList<Game_Button>();
        GameButton.add(new Game_Button(Game.BLOCK_SIZE*9,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.BUTTON_HEIGHT,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT));
        GameButton.add(new Game_Button(Game.BLOCK_SIZE*6,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*4 - Game.BUTTON_HEIGHT- Game.LINE_SIZE*2,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT));
        buttonCount = GameButton.size();

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
        lvlDat[5] = new Rectangle(Game.SPACE_BETWEEN_LINES*2,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE - Game.SPACE_BETWEEN_LINES*5,Game.BLOCK_SIZE*7,Game.LINE_SIZE);
        //Line 7
        lvlDat[6] = new Rectangle(Game.BLOCK_SIZE*11,Game.SPACE_BETWEEN_LINES + Game.LINE_SIZE *3,Game.BLOCK_SIZE*9,Game.BLOCK_SIZE/3);

        //Left side box
        lvlDat[7] = new Rectangle (0,Game.FLOOR_HEIGHT - Game.SPACE_BETWEEN_LINES*2 - Game.BLOCK_SIZE*2,Game.SPACE_BETWEEN_LINES, Game.SPACE_BETWEEN_LINES);

        // bottom line
        lvlDat[8] = new Rectangle(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*80));

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
        lvlDat[20] = new Rectangle(Game.GAME_WIDTH - Game.BLOCK_SIZE*6,(Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*2)+(Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE),Game.LINE_SIZE,Game.BLOCK_SIZE*2);
        //Moving Gate 2
        lvlDat[21] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.SPACE_BETWEEN_LINES*3, Game.BLOCK_SIZE*2, Game.BLOCK_SIZE*3 + Game.LINE_SIZE*2);

        //button for Moving Gate 1
//        lvlDat[22] = new Rectangle(Game.BLOCK_SIZE*9,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.BUTTON_HEIGHT,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        lvlDat[22] = GameButton.get(0).makeRectangle();
        //button for Moving Gate 2
//        lvlDat[23] = new Rectangle(Game.BLOCK_SIZE*6,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*4 - Game.BUTTON_HEIGHT- Game.LINE_SIZE*2,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        lvlDat[23] = GameButton.get(1).makeRectangle();
        //buttons
//        buttons = new Rectangle[2];
//        buttons[0] = new Rectangle(Game.BLOCK_SIZE*9,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*2 - Game.BUTTON_HEIGHT,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
//        buttons[1] = new Rectangle(Game.BLOCK_SIZE*6,Game.FLOOR_HEIGHT - Game.BLOCK_SIZE*4 - Game.BUTTON_HEIGHT- Game.LINE_SIZE*2,Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);


        LevelCreated = true;
    }

    public void createLevel5(Graphics g){
        lvlDat = new Rectangle[1];

        lvlDat[0] = new Rectangle(0, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2,Game.GAME_WIDTH,Game.GAME_HEIGHT/2);

        obstacles = new Obstacle[3];
        obstacles[0] = new Pencil( 700, 400, game);
        obstacles[1] = new Gem( 270, 400, game);
        obstacles[2] = new Fire(570, 430, game);

        LevelCreated = true;
    }

    public void createLevel6(Graphics g){
        lvlDat = new Rectangle[11];
        // bottom line
        lvlDat[0] = new Rectangle(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*80));
        //top line
        lvlDat[1] = new Rectangle(0, Game.SPACE_BETWEEN_LINES*2, Game.GAME_WIDTH - Game.BLOCK_SIZE *3, Game.LINE_SIZE);
        //top right line
        lvlDat[2] = new Rectangle(Game.GAME_WIDTH - Game.BLOCK_SIZE/2, Game.SPACE_BETWEEN_LINES*2, Game.BLOCK_SIZE/2, Game.LINE_SIZE);
        //Right vertical line
        lvlDat[3] = new Rectangle(Game.GAME_WIDTH - Game.BLOCK_SIZE *3,Game.SPACE_BETWEEN_LINES*2, Game.LINE_SIZE, Game.BLOCK_SIZE*3 + Game.LINE_SIZE);
        //Middle Line
        lvlDat[4] = new Rectangle(Game.BLOCK_SIZE*3, Game.SPACE_BETWEEN_LINES*2 + Game.BLOCK_SIZE*3, Game.GAME_WIDTH - Game.BLOCK_SIZE*6, Game.LINE_SIZE);
        //button for Moving Gate 1
        lvlDat[5] = new Rectangle(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*80));

        lvlDat[6] = new Rectangle(0, Game.FLOOR_HEIGHT - Game.JUMP_HEIGHT*2, Game.BUTTON_WIDTH, Game.LINE_SIZE);
        lvlDat[7] = new Rectangle(Game.BLOCK_SIZE*2, Game.FLOOR_HEIGHT - Game.JUMP_HEIGHT, Game.BUTTON_WIDTH, Game.LINE_SIZE);
        lvlDat[8] = new Rectangle(Game.BLOCK_SIZE*4, Game.FLOOR_HEIGHT - Game.JUMP_HEIGHT, Game.BUTTON_WIDTH, Game.LINE_SIZE);
        lvlDat[9] = new Rectangle(Game.BLOCK_SIZE*6, Game.FLOOR_HEIGHT - Game.JUMP_HEIGHT, Game.BUTTON_WIDTH, Game.LINE_SIZE);
        lvlDat[10] = new Rectangle(Game.BLOCK_SIZE*8, Game.FLOOR_HEIGHT - Game.JUMP_HEIGHT, Game.BUTTON_WIDTH, Game.LINE_SIZE);


        obstacles = new Obstacle[3];
        obstacles[0] = new Pencil( 700, 400, game);
        obstacles[1] = new Gem( 270, 400, game);
        obstacles[2] = new Fire(570, 430, game);


        LevelCreated = true;
    }

    public void createLevel7(Graphics g){
        lvlDat = new Rectangle[4];
        // bottom line
        lvlDat[0] = new Rectangle(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*80));
        //top line
        lvlDat[1] = new Rectangle(Game.BLOCK_SIZE, Game.SPACE_BETWEEN_LINES, Game.GAME_WIDTH, Game.LINE_SIZE);
        //top left line
        lvlDat[2] = new Rectangle(0, Game.SPACE_BETWEEN_LINES*3, Game.GAME_WIDTH - Game.BLOCK_SIZE*2, Game.LINE_SIZE);
        //Middle Line
        lvlDat[3] = new Rectangle(Game.BLOCK_SIZE*2, Game.SPACE_BETWEEN_LINES*2 + Game.BLOCK_SIZE*3, Game.GAME_WIDTH, Game.LINE_SIZE);




        obstacles = new Obstacle[3];
        obstacles[0] = new Pencil( 700, 400, game);
        obstacles[1] = new Gem( 270, 400, game);
        obstacles[2] = new Fire(570, 430, game);


        LevelCreated = true;
    }

    public void createLevel8(Graphics g){
        isThereButtons = true;
        lvlDat = new Rectangle[24];
        // bottom line
        lvlDat[0] = new Rectangle(0, Game.FLOOR_HEIGHT, Game.GAME_WIDTH, (int)(Game.SCALE*80));
        // Line 1
        lvlDat[1] = new Rectangle(0, Game.GAME_HEIGHT - Game.SPACE_BETWEEN_LINES*2, Game.GAME_WIDTH + Game.LINE_SIZE, Game.LINE_SIZE);
        // Line 2
        lvlDat[2] = new Rectangle(0, Game.BLOCK_SIZE*3/2, Game.BLOCK_SIZE*2, Game.LINE_SIZE);
        // Line 3
        lvlDat[3] = new Rectangle(Game.BLOCK_SIZE*3, Game.GAME_HEIGHT - Game.BLOCK_SIZE*4 + Game.LINE_SIZE, Game.BLOCK_SIZE*2, Game.LINE_SIZE);
        // Line 4
        lvlDat[4] = new Rectangle(Game.BLOCK_SIZE * 6, Game.GAME_HEIGHT - Game.BLOCK_SIZE*5  , Game.BLOCK_SIZE*3, Game.LINE_SIZE);
        // Line 5
        lvlDat[5] = new Rectangle(Game.BLOCK_SIZE *3/2, Game.GAME_HEIGHT - Game.BLOCK_SIZE*5 + Game.LINE_SIZE, Game.BUTTON_WIDTH, Game.LINE_SIZE);
        // Line 6
        lvlDat[6] = new Rectangle(0, Game.BLOCK_SIZE*3, Game.BLOCK_SIZE, Game.LINE_SIZE);
        // Line 7
        lvlDat[7] = new Rectangle(Game.BLOCK_SIZE * 5, Game.BLOCK_SIZE*3 - Game.LINE_SIZE*2, Game.BUTTON_WIDTH, Game.LINE_SIZE);
        // Line 8
        lvlDat[8] = new Rectangle(Game.BLOCK_SIZE * 3, Game.BLOCK_SIZE*2, Game.BLOCK_SIZE*3/2, Game.LINE_SIZE);
        // Line 9
        lvlDat[9] = new Rectangle(Game.BLOCK_SIZE * 11, Game.BLOCK_SIZE*3, Game.BUTTON_WIDTH, Game.LINE_SIZE);
        // Line 10
        lvlDat[10] = new Rectangle(Game.BLOCK_SIZE * 13 + Game.LINE_SIZE, Game.BLOCK_SIZE*3/2, Game.BLOCK_SIZE*2, Game.LINE_SIZE);
        // Line 11
        lvlDat[11] = new Rectangle(Game.BLOCK_SIZE*13, Game.GAME_HEIGHT - Game.BLOCK_SIZE*4 , Game.BLOCK_SIZE*3, Game.LINE_SIZE);


        //Gate 1
        lvlDat[16] = new Rectangle(Game.SPACE_BETWEEN_LINES*2, Game.GAME_HEIGHT - Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE-4, Game.LINE_SIZE, Game.BLOCK_SIZE*2);
        //Gate 2
        lvlDat[13] = new Rectangle(Game.SPACE_BETWEEN_LINES*4, Game.GAME_HEIGHT - Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE-4, Game.LINE_SIZE, Game.BLOCK_SIZE*2);
        //Gate 3
        lvlDat[14] = new Rectangle(Game.SPACE_BETWEEN_LINES*6, Game.GAME_HEIGHT - Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE-4, Game.LINE_SIZE, Game.BLOCK_SIZE*2);
        //Gate 4
        lvlDat[15] = new Rectangle(Game.SPACE_BETWEEN_LINES*8, Game.GAME_HEIGHT - Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE-4, Game.LINE_SIZE, Game.BLOCK_SIZE*2);
        //Gate 5
        lvlDat[12] = new Rectangle(Game.SPACE_BETWEEN_LINES*10, Game.GAME_HEIGHT - Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE-4, Game.LINE_SIZE, Game.BLOCK_SIZE*2);
        //Gate 6
        lvlDat[17] = new Rectangle(Game.SPACE_BETWEEN_LINES*12, Game.GAME_HEIGHT - Game.SPACE_BETWEEN_LINES*2 + Game.LINE_SIZE-4, Game.LINE_SIZE, Game.BLOCK_SIZE*2);

        //buttons
        lvlDat[18] = new Rectangle(Game.BLOCK_SIZE*4 - Game.BUTTON_WIDTH/2, Game.GAME_HEIGHT - Game.BLOCK_SIZE*4 + Game.LINE_SIZE - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        lvlDat[19] = new Rectangle(0, Game.BLOCK_SIZE*3 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        lvlDat[20] = new Rectangle(Game.BLOCK_SIZE * 3 + Game.BLOCK_SIZE*3/4 - Game.BUTTON_WIDTH/2, Game.BLOCK_SIZE*2 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        lvlDat[21] = new Rectangle(Game.BLOCK_SIZE * 14 + Game.LINE_SIZE - Game.BUTTON_WIDTH/2, Game.BLOCK_SIZE*3/2 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        lvlDat[22] = new Rectangle(Game.BLOCK_SIZE*13 + Game.BLOCK_SIZE*3/2 - Game.BUTTON_WIDTH/2, Game.GAME_HEIGHT - Game.BLOCK_SIZE*4 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        lvlDat[23] = new Rectangle(Game.GAME_WIDTH/2, Game.GAME_HEIGHT - Game.SPACE_BETWEEN_LINES*2 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);


        buttons = new Rectangle[6];
        //Gate 1 Button
        buttons[0] = new Rectangle(Game.BLOCK_SIZE*4 - Game.BUTTON_WIDTH/2, Game.GAME_HEIGHT - Game.BLOCK_SIZE*4 + Game.LINE_SIZE - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        buttons[1] = new Rectangle(0, Game.BLOCK_SIZE*3 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        buttons[2] = new Rectangle(Game.BLOCK_SIZE * 3 + Game.BLOCK_SIZE*3/4 - Game.BUTTON_WIDTH/2, Game.BLOCK_SIZE*2 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        buttons[3] = new Rectangle(Game.BLOCK_SIZE * 14 + Game.LINE_SIZE - Game.BUTTON_WIDTH/2, Game.BLOCK_SIZE*3/2 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        buttons[4] = new Rectangle(Game.BLOCK_SIZE*13 + Game.BLOCK_SIZE*3/2 - Game.BUTTON_WIDTH/2, Game.GAME_HEIGHT - Game.BLOCK_SIZE*4 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);
        buttons[5] = new Rectangle(Game.GAME_WIDTH/2, Game.GAME_HEIGHT - Game.SPACE_BETWEEN_LINES*2 - Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);


        LevelCreated = true;
    }




    /* drawLevel: draws level platforms and buttons. */
    public void drawLevel(Graphics g){
        //print function
//        System.out.println(isThereButtons);
        for (int i = 0; i < lvlDat.length-(buttonCount); i++) {
            g.fillRect(lvlDat[i].x, lvlDat[i].y, lvlDat[i].width, lvlDat[i].height);

        }
        for (int i = lvlDat.length-(buttonCount); i < lvlDat.length; i++) {
            g.setColor(Color.PINK);
            g.fillRect(lvlDat[i].x, lvlDat[i].y, lvlDat[i].width, lvlDat[i].height);

        }
//        if (isThereButtons){
//            for (int j = 0; j < buttons.length; j++){
//            g.setColor(Color.PINK);
//            g.fillRect(buttons[j].x, buttons[j].y, buttons[j].width, buttons[j].height);
//        }

    }




    /* createLevel: uses level variable to call on corresponding level creation method. */
    public void createLevel(Graphics g){
//
        switch(4){
            case 1:
                if(!LevelCreated){
                    createLevel1(g);
                    g.drawImage(animations[aniIndex], Game.BLOCK_SIZE*3, Game.BLOCK_SIZE, 700, 200, null);
                }
                g.drawImage(animations[aniIndex], Game.BLOCK_SIZE*3, Game.BLOCK_SIZE, 700, 200, null);

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
                if(!LevelCreated){
                    createLevel5(g);
                }
                break;
            case 6:
                if(!LevelCreated){
                    createLevel6(g);
                }
                break;
            case 7:
                if(!LevelCreated){
                    createLevel7(g);
                }
                break;
            case 8:
                if(!LevelCreated){
                    createLevel8(g);
                }
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:   // ending
                levelChange++;
                 break;
            case 666:
                if(!LevelCreated){
                    createLevelTEST(g);
                }
            default:
                break;


        }
    }

    private void createLevelTEST(Graphics g) {
        isThereButtons = true;
        lvlDat = new Rectangle[3];
        // floor
        lvlDat[0] = new Rectangle(0, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2,Game.GAME_WIDTH,Game.LINE_SIZE);
        //gate
        lvlDat[1] = new Rectangle( 600,240, Game.LINE_SIZE, Game.BLOCK_SIZE);
        //button
        lvlDat[2] = new Rectangle(300, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2- Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);

        buttons = new Rectangle[1];
        buttons[0] = new Rectangle(300, Game.GAME_HEIGHT/2 + Game.BLOCK_SIZE*2- Game.BUTTON_HEIGHT, Game.BUTTON_WIDTH, Game.BUTTON_HEIGHT);

        LevelCreated = true;
    }

    /* loadAnimations: loads obstacle objects into animations array. */
    public void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.TITLE_ATLAS);
        animations = new BufferedImage[3];
        animations[0] = img.getSubimage( 0, 166, 175, 53);
        animations[1] = img.getSubimage( 175, 166, 195, 53);
        animations[2] = img.getSubimage(370, 166, 210, 53);
    }

    /* updateAnimationTick: animates by cycling through image panels. */
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



}


