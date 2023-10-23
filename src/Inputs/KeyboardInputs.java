package Inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer1().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer1().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer1().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer1().setRight(true);
                break;
            case KeyEvent.VK_UP:
                gamePanel.getGame().getPlayer2().setUp(true);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.getGame().getPlayer2().setLeft(true);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().getPlayer2().setDown(true);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.getGame().getPlayer2().setRight(true);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer1().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer1().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer1().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer1().setRight(false);
                break;
            case KeyEvent.VK_UP:
                gamePanel.getGame().getPlayer2().setUp(false);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.getGame().getPlayer2().setLeft(false);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().getPlayer2().setDown(false);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.getGame().getPlayer2().setRight(false);
                break;
        }
    }
}
