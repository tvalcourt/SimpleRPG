package entity.player;

import util.LoadScript;

import java.util.ArrayList;

/**
 * Represents the main character in the game who you use to battle with
 * Created by Trevor on 6/11/2016.
 */
public class Player {
    protected String name;
    protected int attack, defence, speed, hitpoints, experience;
    protected ArrayList<String[]> inventory;
    private LoadScript scriptLoader;

    public Player(String name){
        this.name = name;
        this.inventory = new ArrayList<>();

        scriptLoader = new LoadScript("src/entity/player/player.lua");
        scriptLoader.runScriptFunction("create", this, 1, 0);
    }

    public LoadScript getScriptLoader(){
        return scriptLoader;
    }

    // Prints to the console that the game is over and the player is dead
    public void defeated(){
        System.out.println("The hero has fallen. Game Over.");
    }

    // Determine if the player is alive based on hitpoints
    public boolean isAlive(){
        if(hitpoints > 0)
            return true;

        return false;
    }

    /**
     * Prints out the players current stats to the console
     */
    public void displayStats(){
        System.out.println("========= Player Stats =========");
        System.out.println("Name: " + name);
        System.out.println("Health: " + hitpoints);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defence);
        System.out.println("Speed: " + speed);
        System.out.println("Experience: " + experience);
        System.out.println("================================");
    }
    /**
     * Accessors and Mutators
     */
    public String getName() { return name; }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefence() {
        return defence;
    }
    public void setDefence(int defence) {
        this.defence = defence;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getHitpoints() {
        return hitpoints;
    }
    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public ArrayList<String[]> getInventory() {
        return inventory;
    }
    public void setInventory(ArrayList<String[]> inventory) { this.inventory = inventory; }
}
