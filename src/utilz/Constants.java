package utilz;

public class Constants {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int IDLE1 = 0;
        public static final int IDLE2 = 1;
        public static final int RUNNING1 = 2;
        public static final int RUNNING2 = 3;
        public static final int JUMP1 = 4;
        public static final int JUMP2 = 5;
        public static final int DEATH = 6;
        public static final int JUMPING1 = 7;
        public static final int JUMPING2 = 8;



        public static int GetSpriteAmount(int player_action){
            switch(player_action){
                case RUNNING1:
                case RUNNING2:
                case DEATH:
                    return 7;
                case IDLE1:
                case IDLE2:
                    return 2;
                case JUMPING1:
                case JUMPING2:
                default:
                    return 1;

            }
        }

    }
    public static class ObstacleConstants{
        public static final int GEM = 0;
        public static final int FIRE = 1;
        public static final int PENCIL = 2;
        public static final int GONE = 3;

    }
}
