package entities;

import java.awt.*;

public class Game_Button {

    float x,y;
    int width,height;

    public Game_Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle makeRectangle(){
        return new Rectangle((int)x ,(int) y, width, height);
    }
}
