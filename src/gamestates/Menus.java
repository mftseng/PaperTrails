package gamestates;

import main.Game;
import ui.Buttons;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menus extends State implements Statemethods{
    private Buttons[] buttons = new Buttons[3];
    private BufferedImage[] title = new BufferedImage[4];

    private int index, aniTick, aniSpeed = 60;

    public Menus(Game game) {
        super(game);
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

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

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
