package entities;

import gamestates.Gamestate;
import gamestates.Playerstate;
import levels.LevelManager;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

public class Player extends MovingEntities {

    private int aniTick, aniIndex, aniSpeed = 17;
    private final int playerNum;
    private int playerAction;
    private boolean moving = false;
    private boolean movingX = false;
    private BufferedImage[][] animations;
    private boolean left, right, up, down;
    private float playerSpeed = 2.0f;
    private LevelManager levelManager;

    private float xDrawOffset = 75 * Game.SCALE;
    private float yDrawOffset = 30 * Game.SCALE;

    //Jumping.Gravity
    private float airSpeed = 0f;
    private float gravity = .15f * Game.SCALE;
    private float jumpSpeed = -6.5f * Game.SCALE;
    private boolean inAirTest = true;
    private boolean inAir;
    private boolean inAirNotButton = false;

    private int onObstacle;

    private boolean died = false;
    private boolean gotPencil = false;

    private boolean gateClosed = true;


    private Playerstate playerstate = Playerstate.ACTIVE;
    private static int gemCounter = 0;

    private int onButton = -1, tempOnButton ;
    private boolean buttonPressedState, buttonPressed;

    private boolean flipped;


    public Player(float x, float y, int playerNum, Game game) {
        super(x, y, Game.CHAR_WIDTH, Game.CHAR_HEIGHT, game);
        this.playerNum = playerNum;
        loadAnimations();
        levelManager = new LevelManager(game);
        innitHitBox(x, y, 43 * Game.SCALE, 90 * Game.SCALE);


    }


    public void update() {
        if (playerstate == Playerstate.ACTIVE) {
//            updatePos();
            updatePosTest();
            updateHitbox();
            updateAnimationTick();
            setAnimation();

        }
        else if(playerstate == Playerstate.DYING) {
            playerAction = DEATH;
            aniTick++;
            if (aniTick > aniSpeed){
                aniTick = 0;
                if (aniIndex < 6)
                    aniIndex ++;
                else
                    playerstate = Playerstate.DEAD;
            }

        }
    }

    public void die() {
        if (playerstate == Playerstate.ACTIVE) {
            playerstate = Playerstate.DYING;

        }
    }




    public void render(Graphics g) {
        boolean isFacingLeft = (left && !right);
        boolean isFacingRight = (right && !left);

        BufferedImage playerImage = animations[playerAction][aniIndex];
        int drawX = (int) (hitbox.x - xDrawOffset);
        int drawY = (int) (hitbox.y - yDrawOffset);

        if ((isFacingLeft && playerNum == 1) || (isFacingLeft && playerNum == 2)) {

            playerImage = flipImage(playerImage);
            // Adjust the drawX position to compensate for the flipped image
            if (playerImage != null)
                drawX += playerImage.getWidth() - (this.playerNum == 1 ? Game.CHAR1_WIDTH : Game.CHAR2_WIDTH);
        }

        int width = (this.playerNum == 1) ? Game.CHAR1_WIDTH : Game.CHAR2_WIDTH;
        int height = (this.playerNum == 1) ? Game.CHAR1_HEIGHT : Game.CHAR2_HEIGHT;
        g.drawImage(playerImage, drawX, drawY, width, height, null);

        drawHitbox(g);
    }



    public void setDirection(int direction) {

    }


    public void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[9][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 160, i * 160, 120, 130);
            }
        }
        animations[7][0] = animations[4][4];
        animations[8][0] = animations[5][2];


    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;


            }
        }

    }


    private void setAnimation() {
        if(movingX) {
            if (playerNum == 1) {
                playerAction = RUNNING1;
            } else {
                playerAction = RUNNING2;
            }
        }
        else if (inAir){
            if (playerNum == 1) {
                playerAction = JUMPING1;
            } else {
                playerAction = JUMPING2;
            }
        }
        else if (died){
            playerAction = DEATH;
//            if (this.playerNum == 1)
//                Gamestate.state = Gamestate.PLAYER1DEAD;
//            else if (this.playerNum == 2){
//                Gamestate.state = Gamestate.PLAYER2DEAD;
//            }
        }
        else if (playerNum == 1)
            playerAction = IDLE1;
        else
            playerAction = IDLE2;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public int getAniTick() {
        return aniTick;
    }

    public void setAniTick(int aniTick) {
        this.aniTick = aniTick;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    private void updatePos() {
        Rectangle[] lvlDat = levelManager.getLvlData(); // Access the lvlDat array
        if (lvlDat != null) {
            inAir = CanMoveHere(hitbox.x, hitbox.y + airSpeed, (int) hitbox.width, (int) hitbox.height, lvlDat);

            //Fix for sticky ceilings
            if (!inAir) {
                if (CanMoveHere(hitbox.x, hitbox.y + 5, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
                    inAir = true;
                    airSpeed = 0;
                    hitbox.y += 1;
                    y = hitbox.y;
                }
            }
        }

        if (levelManager.getObstacles() != null) { //get Obstacles should never be null so this should be removed ideally
            if (levelManager.getAreThereObstacles()) {
                onObstacle = onObstacle(hitbox.x, hitbox.y, (int) hitbox.width, (int) hitbox.height, levelManager.getObstacles());
                if (onObstacle != -1) {
                    if (levelManager.getObstacles()[onObstacle] instanceof Gem) {
                        Gem gem =  (Gem)levelManager.getObstacles()[onObstacle];
                        if (!gem.getCollected()) {
                            gem.setCollected();
                            gemCounter++;
                        }
                    } else if (levelManager.getObstacles()[onObstacle] instanceof Fire) {
                        died = true;
                        System.out.println(onObstacle);

                    }
                    else if (levelManager.getObstacles()[onObstacle] instanceof NME) {
                        died = true;
                        System.out.println(onObstacle);
                    }
                    else if (levelManager.getObstacles()[onObstacle] instanceof Pencil) {
                        Gamestate.state = Gamestate.LEVELCOMPLETE;
                    }
                }
            }
        }


        if (levelManager.getButtons() != null) {
            onButton = CanMoveHereObject(hitbox.x, hitbox.y + 10, (int) hitbox.width, (int) hitbox.height, levelManager.getButtons());
            int GateStartingIndex = levelManager.getLvlData().length - levelManager.getButtons().length * 2;
            if (onButton > -1) {
                if (levelManager.getLvlData()[GateStartingIndex + onButton].height != 0) {
                    //move level object
                    levelManager.getLvlData()[GateStartingIndex + onButton].height-=2;

                    if (!buttonPressedState) {
                        levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + onButton].y += (int) (20 * Game.SCALE);
                        levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + onButton].height = Game.PRESSED_BUTTON_HEIGHT;
                        levelManager.getButtons()[onButton].y = levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + onButton ].y;
                        levelManager.getButtons()[onButton].height = levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + onButton].height;
                        tempOnButton = onButton;
                        buttonPressedState = true;

                    }

                }
            } else {
                for (int i = 0; i < levelManager.getButtons().length; i++) {
                    if (CanMoveHere(levelManager.getLvlData()[GateStartingIndex + i].x, levelManager.getLvlData()[GateStartingIndex + i].height + 10, 1, 1, lvlDat)) {
                        levelManager.getLvlData()[GateStartingIndex + onButton + 1 + i].height++;
                    }
                }
                if (buttonPressedState && !inAir) {
                    levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + tempOnButton].y -= (int)(20 * Game.SCALE);
                    levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + tempOnButton].height = Game.BUTTON_HEIGHT;
                    levelManager.getButtons()[tempOnButton].y = levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + tempOnButton].y;
                    levelManager.getButtons()[tempOnButton].height = levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + tempOnButton].height;
                    buttonPressedState = false;
                }

            }
        }





        float xSpeed = 0;
        if (!left && !right && !up && !inAir) {
            return;
        }

        movingX = false;

        if (left)
            xSpeed -= playerSpeed;
        if (right)
            xSpeed += playerSpeed;


        if (up) {
            if (!inAir) {
                airSpeed = jumpSpeed;
                inAir = true;
            }
        }

        //Gravity Logic + Horizontal movement logic
        if (inAir) { //Checks if you are in the air, then moves down if true
            if(CanMoveHere(hitbox.x, hitbox.y + airSpeed,(int) hitbox.width, (int) hitbox.height, lvlDat)){
                hitbox.y += airSpeed;
                y = hitbox.y;
                airSpeed += gravity;
                if(right || left)
                    updateXPos(xSpeed);
            }
        } else { //If not in air, reset gravity
            airSpeed = 0;
            if(right || left)
                updateXPos(xSpeed);

        }

    }

    private void updatePosTest() {
        Rectangle[] lvlDat = levelManager.getLvlData(); // Access the lvlDat array

        //Obstacles logic
        if (levelManager.getObstacles() != null) { //get Obstacles should never be null so this should be removed ideally
            if (levelManager.getAreThereObstacles()) {
                onObstacle = onObstacle(hitbox.x, hitbox.y, (int) hitbox.width, (int) hitbox.height, levelManager.getObstacles());
                if (onObstacle != -1) {
                    if (levelManager.getObstacles()[onObstacle] instanceof Gem) {
                        Gem gem = (Gem) levelManager.getObstacles()[onObstacle];
                        if (!gem.getCollected()) {
                            gem.setCollected();
                            gemCounter++;
                        }
                    } else if (levelManager.getObstacles()[onObstacle] instanceof Fire) {
                        died = true;
                        System.out.println(onObstacle);

                    } else if (levelManager.getObstacles()[onObstacle] instanceof NME) {
                        died = true;
                        System.out.println(onObstacle);
                    } else if (levelManager.getObstacles()[onObstacle] instanceof Pencil) {
                        Gamestate.state = Gamestate.LEVELCOMPLETE;
                    }
                }
            }
        }




        movingX = false;
        float xSpeed = 0;

        if (!left && !right && !up && !inAirTest && onButton == -1 && gateClosed) {
            return;
        }
        System.out.println("active");



        if (left)
            xSpeed -= playerSpeed;
        if (right)
            xSpeed += playerSpeed;


        if (up) {
            if (!inAirTest) {
                airSpeed = jumpSpeed;
                inAirTest = true;
            }
        }


        if (lvlDat != null) {
            //
            if (inAirTest) { //Checks if you are in the air, then moves down if true
                hitbox.y += airSpeed;
                y = hitbox.y;
                airSpeed += gravity;
                if (right || left)
                    updateXPos(xSpeed);


                inAirTest = CanMoveHere(hitbox.x, hitbox.y + airSpeed, (int) hitbox.width, (int) hitbox.height, lvlDat);
                //Sticky ceiling fix
                if (!inAirTest) {
                    if (CanMoveHere(hitbox.x, hitbox.y + 5, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
                        inAirTest = true;
                        airSpeed = 0;
                        hitbox.y += 1;
                        y = hitbox.y;
                    }
                }



            } else { //If not in air, reset gravity
                airSpeed = 0;
                if (right || left) {
                    updateXPos(xSpeed);
                    inAirTest = CanMoveHere(hitbox.x, hitbox.y + airSpeed, (int) hitbox.width, (int) hitbox.height, lvlDat);
                    if (!inAirTest) {
                        if (CanMoveHere(hitbox.x, hitbox.y + 5, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
                            inAirTest = true;
                            airSpeed = 0;
                            hitbox.y += 1;
                            y = hitbox.y;
                        }
                    }
                }

            }
        }

        //Button Logic
        if (levelManager.getButtons() != null) {
            onButton = CanMoveHereObject(hitbox.x, hitbox.y + 10, (int) hitbox.width, (int) hitbox.height, levelManager.getButtons()); //Checks if button is being pressed
            int GateStartingIndex = levelManager.getLvlData().length - levelManager.getButtons().length * 2; //gets the gate starting index from lvlDat
            if (onButton > -1) { // If you are on ANY button,
                if (levelManager.getLvlData()[GateStartingIndex + onButton].height != 10) { //if gate is not completely closed

                    levelManager.getLvlData()[GateStartingIndex + onButton].height -= 2; //move level object


                    if (!buttonPressedState) { //If the button is not pressed, press
                        levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + onButton].y += (int) (20 * Game.SCALE);
                        levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + onButton].height = Game.PRESSED_BUTTON_HEIGHT;
                        levelManager.getButtons()[onButton].y = levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + onButton].y;
                        levelManager.getButtons()[onButton].height = levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + onButton].height;
                        tempOnButton = onButton;
                        buttonPressedState = true;

                    }
                    gateClosed = false;

                }
            } else { // if not on button
                if (!gateClosed) { //if gate isn't closed completely
                    boolean gateMoved = false;
                for (int i = 0; i < levelManager.getButtons().length; i++) { // If not on the button, close the gate
                    if (levelManager.getLvlData()[GateStartingIndex + i].height != Game.BLOCK_SIZE*2) {
                        levelManager.getLvlData()[GateStartingIndex + onButton + 1 + i].height++;
                        gateMoved = true;
                    }
                }
                    gateClosed = !gateMoved;
//                    System.out.println(gateMoved);
                }
                if (buttonPressedState && !inAirTest) { //If button is not being pressed, release
                    levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + tempOnButton].y -= (int) (20 * Game.SCALE);
                    levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + tempOnButton].height = Game.BUTTON_HEIGHT;
                    levelManager.getButtons()[tempOnButton].y = levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + tempOnButton].y;
                    levelManager.getButtons()[tempOnButton].height = levelManager.getLvlData()[GateStartingIndex + levelManager.getButtons().length + tempOnButton].height;
                    buttonPressedState = false;
                }

            }
        }

    }

    private void updateXPos(float xSpeed) { //Horizontal movement logic
        Rectangle[] lvlDat = levelManager.getLvlData(); // Access the lvlDat array
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, (int) hitbox.width, (int) hitbox.height, lvlDat)) {
            hitbox.x += xSpeed;
            x = hitbox.x;
            movingX = true;
        }
    }



    public void setPlayerAction(int newAction){this.playerAction = newAction;}

    public int getOnObstacle(){return onObstacle;}

    public boolean isDead(){
        return died;
    }

    public static int getGemCounter(){
        return gemCounter;
    }

    private BufferedImage flipImage(BufferedImage image) {
        if (image != null) {
            BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // Create a graphics object from the flipped image
            Graphics2D g = flippedImage.createGraphics();

            // Flip the image horizontally using transformation
            g.drawImage(image, image.getWidth(), 0, 0, image.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);

            // Dispose the graphics object
            g.dispose();

            return flippedImage;
        }
        return image;
    }




}






