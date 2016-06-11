import entity.player.Player;

import java.util.Scanner;

/**
 * Represents the central game class. Handles the main game loop
 * Created by Trevor on 6/11/2016.
 */
public class SimpleRPG {

    static Player player; // character controlled by the player

    public SimpleRPG(){
        player = new Player(promptHeroName());
    }

    public void play() throws InterruptedException {
        player.displayStats();
    }

    public String promptHeroName(){
        String name;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of your Hero: ");
        name = scan.nextLine();
        scan.close();

        return name;
    }
}
