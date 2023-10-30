package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{
    private Player player1;
    private Player player2;
    private LevelManager levelManager;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player1 = new Player(50f * Game.SCALE,50f * Game.SCALE, 1, game);
        player2 = new Player(250f * Game.SCALE, 50f * Game.SCALE, 2, game);

    }

    public void windowFocusLost(){
        player1.resetDirBooleans();
        player2.resetDirBooleans();

    }

    @Override
    public void update() {
        levelManager.update();
        player1.update();
        player2.update();

    }

    @Override
    public void draw(Graphics g) {
        levelManager.render(g);
        player1.render(g);
        player2.render(g);

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


}
