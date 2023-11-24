package gamestates;

import entities.Player;
import main.Game;
import ui.CompleteButton;
import ui.MenuButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class LevelComplete extends State implements Statemethods{
    private CompleteButton[] buttons = new CompleteButton[3];
    private BufferedImage[] completeTitle = new BufferedImage[4];
    private BufferedImage[][] grades = new BufferedImage[3][4];
    private int index, aniTick, aniSpeed =60;
    private int grades_index;
    Map<Integer, Integer> score_to_grade;
    public LevelComplete(Game game) {
        super(game);
        loadButtons();
        for (CompleteButton button : buttons){
            System.out.print(button.getBounds());
        }
        loadBackground();
        score_to_grade = Map.ofEntries(entry(3,0), entry(2,1), entry(1,2), entry(0,3));
    }

    private int calculateScore(){
        return score_to_grade.get(Player.getGemCounter());
    }

    private void loadButtons() {
        buttons[0] = new CompleteButton(Game.GAME_WIDTH/2, (int) (315*Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new CompleteButton(Game.GAME_WIDTH/2, (int) (435*Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new CompleteButton(Game.GAME_WIDTH/2, (int) (550*Game.SCALE), 2, Gamestate.QUIT);
    }

    private void loadBackground(){
        // title
        BufferedImage menuImg = LoadSave.GetSpriteAtlas(LoadSave.COMPLETE_TITLE);
        for (int i = 0; i < 3; i++){
            completeTitle[i] = menuImg.getSubimage(415 * i, 0, 415, 120);
        }
        completeTitle[3] = completeTitle[1];

        // grade
        BufferedImage scoreImg = LoadSave.GetSpriteAtlas(LoadSave.GRADES);
        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 4; j++){
                grades[i][j] = scoreImg.getSubimage(i * 150, j * 130, 150, 130);
            }
        }

    }

    private void updateBackground(){
        aniTick ++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            index ++;
            grades_index ++;
            if(index>=4){
                index = 0;
            }
            if(grades_index >= 3){
                grades_index = 0;
            }
        }

    }


    @Override
    public void update() {
        updateBackground();
        for (CompleteButton completeButton : buttons){
            completeButton.update();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(completeTitle[index], 445, 100, 300, 100, null);
        g.drawImage(grades[grades_index][calculateScore()], 740, 80, 150,130, null);
        for (CompleteButton completeButton: buttons){
            completeButton.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (CompleteButton completeButton : buttons){
            if(isIn(e, completeButton)){
                completeButton.setMousePressed(true);
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (CompleteButton completeButton : buttons){
            if(isIn(e, completeButton)){
                if(completeButton.isMousePressed()){
                    completeButton.applyGamestate();
                    break;
                }
            }
        }
        resetButtons();

    }

    private void resetButtons() {
        for(CompleteButton completeButton: buttons){
            completeButton.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (CompleteButton completeButton : buttons){
            completeButton.setMouseOver(false);
        }

        for (CompleteButton completeButton : buttons){
            if (isIn(e, completeButton)){
                System.out.println(isIn(e, completeButton));
                completeButton.setMouseOver(true);
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
