package main;


import entities.Player;

import gamestates.*;
import levels.LevelManager;

import javax.sound.sampled.Line;
import java.awt.*;

import static java.awt.SystemColor.menu;

public class Game implements Runnable{
    private Playing playing;
    private GMenu menu;


    private LevelManager levelManager;
    public final static float SCALE = .6f;
    public final static int BLOCK_SIZE = (int)(120 * SCALE);
    public final static int GAME_WIDTH = (int)(1920 * SCALE);
    public final static int GAME_HEIGHT = (int)(1080 * SCALE);
    public final static int FLOOR_HEIGHT = GAME_HEIGHT - (int) (80*SCALE);
    public final static int LINE_SIZE = (int) (SCALE * 20);
    public final static int SPACE_BETWEEN_LINES = BLOCK_SIZE + LINE_SIZE;
    public final static int JUMP_HEIGHT = LINE_SIZE*8;
    public final static int BUTTON_HEIGHT = (int)(30 * SCALE);
    public final static int BUTTON_WIDTH = (int)(60 * SCALE);
    public final static int PRESSED_BUTTON_HEIGHT = (int)(10 * SCALE);

    public final static int CHAR1_WIDTH = (int) (140 * Game.SCALE);
    public final static int CHAR1_HEIGHT = (int) (130 * Game.SCALE);
    public final static int CHAR2_WIDTH = (int) (140 * Game.SCALE);
    public final static int CHAR2_HEIGHT = (int) (140 * Game.SCALE);
    public final static int CHAR_WIDTH = (int) (140 * Game.SCALE);
    public final static int CHAR_HEIGHT = (int) (140 * Game.SCALE);




    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player1;
    private Player player2;



    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();


        startGameLoop();
    }
    private void initClasses(){
        menu = new GMenu(this);
        playing = new Playing(this);


    }
    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        switch(Gamestate.state){
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
    }


    public void render(Graphics g){
        switch(Gamestate.state){
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case QUIT:
                System.exit(0);
                break;
            default:
                break;
        }



    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;


        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF +=(currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                updates ++;
                deltaU --;
            }

            if(deltaF >= 1){
                gamePanel.repaint();
                frames ++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
//                System.out.println("FPS: " + frames + " UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    public void windowFocusLost(){
        if (Gamestate.state == Gamestate.PLAYING){
            playing.getPlayer1().resetDirBooleans();
            playing.getPlayer2().resetDirBooleans();
        }

    }

    public Player getPlayer(int playerNum){
        if (playerNum == 1){return player1;}
        else {return player2;}
    }

    public LevelManager getLvlManager(){
        return levelManager;
    }

    public GMenu getMenu(){
        return menu;
    }

    public Playing getPlaying(){
        return playing;
    }





}