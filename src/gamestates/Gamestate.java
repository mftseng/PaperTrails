package gamestates;

public enum Gamestate {
    PLAYING, MENU, PLAYER1DEAD, PLAYER2DEAD, GAMEOVER, LEVELCOMPLETE;

    public static Gamestate state = MENU;
}


