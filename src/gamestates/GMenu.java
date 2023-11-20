package gamestates;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GMenu extends State implements Statemethods{
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage[] menuTitle = new BufferedImage[4];
    private int index, aniTick, aniSpeed =60;
    public GMenu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2, (int) (315*Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH/2, (int) (435*Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH/2, (int) (550*Game.SCALE), 2, Gamestate.QUIT);
    }

    private void loadBackground(){
        BufferedImage menuImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_TITLE);
        for (int i = 0; i < 3; i++){
            menuTitle[i] = menuImg.getSubimage(390 * i, 0, 390, 170);
        }
        menuTitle[3] = menuTitle[1];

    }

    private void updateBackground(){
        aniTick ++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            index ++;
            if(index>=4){
                index = 0;
            }
        }

    }

    @Override
    public void update() {
        updateBackground();
        for (MenuButton menuButton : buttons){
            menuButton.update();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menuTitle[index], 445, 100, 300, 100, null);
        for (MenuButton menuButton: buttons){
            menuButton.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton menuButton : buttons){
            if(isIn(e, menuButton)){
                menuButton.setMousePressed(true);
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton menuButton : buttons){
            if(isIn(e, menuButton)){
                if(menuButton.isMousePressed()){
                    menuButton.applyGamestate();
                    break;
                }
            }
        }
        resetButtons();

    }

    private void resetButtons() {
        for(MenuButton menuButton: buttons){
            menuButton.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton menuButton : buttons){
            menuButton.setMouseOver(false);
        }

        for (MenuButton menuButton : buttons){
            if (isIn(e, menuButton)){
                menuButton.setMouseOver(true);
                break;
            }
        }



    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            Gamestate.state = Gamestate.PLAYING;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
