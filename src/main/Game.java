package main;


import entities.Player;

import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable{
    private LevelManager levelManager;
    public final static float SCALE = .5f;
    public final static int BLOCK_SIZE = (int)(120 * SCALE);
    public final static int GAME_WIDTH = (int)(1920 * SCALE);
    public final static int GAME_HEIGHT = (int)(1080 * SCALE);
    public final static int FLOOR_HEIGHT = GAME_HEIGHT - (int)(30*SCALE);
    public final static int LINE_SIZE = (int) (SCALE *20);
    public final static int SPACE_BETWEEN_LINES = BLOCK_SIZE + LINE_SIZE;



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

    private void initClasses() {
        player1 = new Player(200f,200f, 1);
        player2 = new Player(275f, 200f, 2);
        levelManager = new LevelManager(this);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player1.update();
        player2.update();
        levelManager.update();
    }


    public void render(Graphics g){
        levelManager.draw(g);
        levelManager.render(g);
        player1.render(g);
        player2.render(g);


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
                System.out.println("FPS: " + frames + " UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    public void windowFocusLost(){
        player1.resetDirBooleans();
        player2.resetDirBooleans();

    }

    public Player getPlayer(int playerNum){
        if (playerNum == 1){return player1;}
        else {return player2;}
    }



}

