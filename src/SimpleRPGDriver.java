/**
 * Driver class for running the Console based RPG game
 * Created by Trevor on 6/11/2016.
 */
public class SimpleRPGDriver {

    public static SimpleRPG game;

    public static void main(String[] args) throws InterruptedException {
        initGame();
    }

    public static void initGame() throws InterruptedException {
        game = new SimpleRPG();
        game.play();
    }
}
