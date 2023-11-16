package gamestates;

import entities.Eraser;
import entities.Player;
import levels.LevelManager;
import main.Game;
import entities.Obstacle;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.HelpMethods.*;

public class Playing extends State implements Statemethods{
    private Player player1;
    private Player player2;
    private LevelManager levelManager;
    private boolean PlayerCollision = false;
    private static boolean playerDying = false;
    private static boolean GameOver;
    private BufferedImage gemCNT;
    Font gemFont;
    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
//        player1 = new Player(300f * Game.SCALE,Game.GAME_HEIGHT -120, 1, game);
//        player2 = new Player(50f * Game.SCALE, Game.GAME_HEIGHT -120, 2, game);
        player1 = new Player(50f * Game.SCALE,0, 1, game);
        player2 = new Player(50f * Game.SCALE, 0, 2, game);
        gemCNT = LoadSave.GetSpriteAtlas(LoadSave.OBBY_ATLAS);
        importFont();


    }

    public void windowFocusLost(){
        player1.resetDirBooleans();
        player2.resetDirBooleans();

    }

    public void PlayerCollided(Player player1, Player player2) {
        Rectangle[] P2 = new Rectangle[1];
        P2[0] = new Rectangle((int)player2.getHitbox().x, (int)player2.getHitbox().y, (int)player2.getHitbox().width, (int)player2.getHitbox().height);
        if (CanMoveHere(player1.getHitbox().x, player1.getHitbox().y, (int) player1.getHitbox().width, (int)player1.getHitbox().height, P2))
            PlayerCollision = false;
        else
            PlayerCollision = true;
    }

    @Override
    public void update() {
        if(Gamestate.state != Gamestate.LEVELCOMPLETE || Gamestate.state != Gamestate.GAMEOVER) {
            levelManager.update();
            player1.update();
            player2.update();

            if (player1.isDead()) {
                player1.die();
            }
            if (player2.isDead()) {
                player2.die();
            }
        }


//        for (Obstacle obstacle : levelManager.getObstacles()){
//            obstacle.update();
//        }

    }






    @Override
    public void draw(Graphics g) {
        if(Gamestate.state != Gamestate.LEVELCOMPLETE || Gamestate.state != Gamestate.GAMEOVER) {
            levelManager.render(g);
            player1.render(g);
            player2.render(g);
            if(levelManager.getObstacles() != null) {
                for (Obstacle obstacle : levelManager.getObstacles()) {
                    obstacle.render(g);
                    obstacle.update();
                }
            }
            BufferedImage gemImg = gemCNT.getSubimage(0, 0, 70, 65);
            g.drawImage(gemImg, 10, 590, 105, 80, null);
            g.setFont(gemFont);
            g.setColor(Color.black);
            g.drawString("x" + Player.getGemCounter(), 105, Game.GAME_HEIGHT- 10);
        }
        else if (Gamestate.state == Gamestate.LEVELCOMPLETE){
            g.drawRect(0, 0, 800, 800);
            g.setFont(gemFont);
            g.setColor(Color.BLACK);
            g.drawString("LEVEL COMPLETE", 80, 400);
        }


    }

    private void importFont(){
        try {
            InputStream is = getClass().getResourceAsStream("/gemCounterFont.ttf");
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
            gemFont = baseFont.deriveFont(35f);
        }
        catch(FontFormatException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }






    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                player1.setUp(true);
                break;
            case KeyEvent.VK_A:
                player1.setLeft(true);
                break;
            case KeyEvent.VK_S:
                player1.setDown(true);
                break;
            case KeyEvent.VK_D:
                player1.setRight(true);
                break;
            case KeyEvent.VK_UP:
                player2.setUp(true);
                break;
            case KeyEvent.VK_LEFT:
                player2.setLeft(true);
                break;
            case KeyEvent.VK_DOWN:
                player2.setDown(true);
                break;
            case KeyEvent.VK_RIGHT:
                player2.setRight(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state = Gamestate.MENU;
                break;
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                player1.setUp(false);
                break;
            case KeyEvent.VK_A:
                player1.setLeft(false);
                break;
            case KeyEvent.VK_S:
                player1.setDown(false);
                break;
            case KeyEvent.VK_D:
                player1.setRight(false);
                break;
            case KeyEvent.VK_UP:
                player2.setUp(false);
                break;
            case KeyEvent.VK_LEFT:
                player2.setLeft(false);
                break;
            case KeyEvent.VK_DOWN:
                player2.setDown(false);
                break;
            case KeyEvent.VK_RIGHT:
                player2.setRight(false);
                break;
        }
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public static void setPlayerDying(boolean died){
        playerDying = died;
    }

    public static boolean getPlayerDying(){
        return playerDying;
    }

    public static void GameOver(){

    }





}
