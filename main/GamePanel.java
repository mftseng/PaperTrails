package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;

    private long lastCheck;
    private int frames;
    
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 17;


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

    public void changeYDelta(int value){
        this.yDelta += value;

    }

    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateAnimationTick();
        g.drawImage(animations[2][aniIndex], (int) xDelta, (int) yDelta, 120, 130, null);




    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 6){
                aniIndex = 0;
            }
        }
    }


}
