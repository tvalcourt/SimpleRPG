package main;

import assets.quest.Quest;
import entity.enemy.Enemy;
import entity.player.Player;

import java.util.Scanner;

/**
 * Represents the central game class. Handles the main game loop
 * Created by Trevor on 6/11/2016.
 */
public class SimpleRPG {

    public static Player player; // character controlled by the player

    public SimpleRPG(){
        player = new Player(promptHeroName());
    }

    public void play() throws InterruptedException {
        System.out.println("A Goblin appeared! Initiating Battle.");
        Enemy goblin = new Enemy("Goblin", "Fiend");

        // Fight the goblin to the death
        while(goblin.isAlive())
            player.getScriptLoader().runScriptFunction("battle", player, goblin);

        Enemy goblin2 = new Enemy("Goblin", "Fiend");
        while(goblin2.isAlive())
            player.getScriptLoader().runScriptFunction("battle", player, goblin2);

        System.out.println();

        Quest intro = new Quest("Introduction");
    }

    public String promptHeroName(){
        String name;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of your Hero: ");
        name = scan.nextLine();
        scan.close();

        return name;
    }

    /**
     * @return The instance of the player
     */
    public static Player getPlayer(){
        return player;
    }
}
