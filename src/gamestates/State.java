package gamestates;

import main.Game;
import ui.CompleteButton;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;

    public State(Game game) {
        this.game = game;

    }
    public boolean isIn(MouseEvent e, MenuButton menuButton){
        return menuButton.getBounds().contains(e.getX(), e.getY());

    }
    public boolean isIn(MouseEvent e, CompleteButton completeButton){
        return completeButton.getBounds().contains(e.getX(), e.getY());

    }

    public Game getGame(){
        return game;
    }
}
