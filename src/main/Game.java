package main;


import entities.Player1;
import entities.Player2;

import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable{
    public final static int TILES_DEFAULT_SIZE = 32;
    private LevelManager levelManager;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player1 player1;
    private Player2 player2;


    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();


        startGameLoop();
    }

    private void initClasses() {
        player1 = new Player1(200f,200f);
        player2 = new Player2(275f, 200f);
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

    public Player1 getPlayer1(){
        return player1;
    }

    public Player2 getPlayer2(){
        return player2;
    }



}

