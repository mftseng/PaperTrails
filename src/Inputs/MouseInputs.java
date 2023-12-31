package Inputs;

import gamestates.Gamestate;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Gamestate.state){
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
            default:
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(Gamestate.state){
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
            case LEVELCOMPLETE:
                gamePanel.getGame().getLevelComplete().mousePressed(e);
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
            default:
                break;
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(Gamestate.state){
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
            case LEVELCOMPLETE:
                gamePanel.getGame().getLevelComplete().mouseReleased(e);
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
            default:
                break;
        }

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch(Gamestate.state){
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
            case LEVELCOMPLETE:
                gamePanel.getGame().getLevelComplete().mouseMoved(e);
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
            default:
                break;
        }

    }

    }
