package gamestates;

public enum Gamestate {
    PLAYING, MENU, OPTIONS, QUIT, PLAYER1DEAD, PLAYER2DEAD, GAMEOVER, LEVELCOMPLETE;

    public static Gamestate state = MENU;
}


