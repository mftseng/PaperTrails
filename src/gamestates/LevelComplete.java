package gamestates;

import levels.LevelManager;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class LevelComplete extends State implements Statemethods{
    private LevelManager levelManager;
    private BufferedImage[] completeTitle;
    private BufferedImage[][] pauseButtons;
    private BufferedImage[][] grades;

    public LevelComplete(Game game) {
        super(game);

    }

    private void loadAnimations(){
        completeTitle = new BufferedImage[4];
        pauseButtons = new BufferedImage[5][3];
        grades = new BufferedImage[3][4];
        BufferedImage titleTemp = LoadSave.GetSpriteAtlas(LoadSave.COMPLETE_TITLE);
        BufferedImage buttonsTemp = LoadSave.GetSpriteAtlas(LoadSave.PAUSEBUTTONS);
        BufferedImage gradesTemp = LoadSave.GetSpriteAtlas(LoadSave.GRADES);

        for (int i = 0; i < 3; i++){
            completeTitle[i] = titleTemp.getSubimage(415 * i, 0, 415, 145);
        }
        completeTitle[3] = completeTitle[1];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 4; j++) {
                grades[i][j] = gradesTemp.getSubimage(i * 155, j * 130, 155, 130);
            }
        }

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 3; j++){
                pauseButtons[i][j] = buttonsTemp.getSubimage(i * 140, j * 100, 140, 100);
            }
        }
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g){

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
