package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;


public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;

    private long lastCheck;
    private int frames;
    
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 17;
    private int playerAction = IDLE1;
    private int playerDir = -1;
    private boolean moving = false;


    public GamePanel(){
        mouseInputs = new MouseInputs(this);

        setPanelSize();
        importImage();
        loadAnimations();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void loadAnimations() {
        animations = new BufferedImage[7][7];

        for(int i = 0; i < animations.length; i++)
            for (int j = 0; j < animations[i].length; j++)
                animations[i][j] = img.getSubimage(j * 160, i * 160, 120, 130);


    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/Stick_figs.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }


    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value){
        this.xDelta += value;
        repaint();
    }

    public void setDirection(int direction){
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        setAnimation();
        updatePos();

        updateAnimationTick();
        g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 120, 130, null);




    }

    private void updatePos() {
        if(moving){
            switch(playerDir){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -=5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    private void setAnimation() {
        if(moving)
            playerAction = RUNNING1;
        else
            playerAction = IDLE1;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }


}
