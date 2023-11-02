package gamestates;

import entities.Player;
import levels.Level;
import levels.LevelManager;
import main.Game;
import entities.Obstacle;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static utilz.HelpMethods.*;
import static levels.LevelManager.*;
public class Playing extends State implements Statemethods{
    private Player player1;
    private Player player2;
    private LevelManager levelManager;
    private boolean PlayerCollision = false;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
//        player1 = new Player(300f * Game.SCALE,Game.GAME_HEIGHT -100, 1, game);
//        player2 = new Player(400f * Game.SCALE, Game.GAME_HEIGHT -100, 2, game);
        player1 = new Player(50f * Game.SCALE,50f * Game.SCALE, 1, game);
        player2 = new Player(250f * Game.SCALE, 50f * Game.SCALE, 2, game);
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
        levelManager.update();
        player1.update();
        player2.update();

//        for (Obstacle obstacle : levelManager.getObstacles()){
//            obstacle.update();
//        }

    }





    @Override
    public void draw(Graphics g) {
        levelManager.render(g);
        player1.render(g);
        player2.render(g);
        for (Obstacle obstacle : levelManager.getObstacles()){
            obstacle.render(g);
            obstacle.update();
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




}
