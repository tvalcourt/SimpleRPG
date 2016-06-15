package entity.player;

import assets.item.Drop;
import assets.item.InventoryItem;
import util.LoadScript;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents the main character in the game who you use to battle with
 * Created by Trevor on 6/11/2016.
 */
public class Player {
    protected String name;
    protected int attack, defence, speed, maxHitpoints, currentHitpoints, experience;
    protected HashMap<String, InventoryItem> inventory;
    private LoadScript scriptLoader;

    public Player(String name){
        this.name = name;
        this.inventory = new HashMap<>();

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

    /**
     *  Determine if the player is alive based on hitpoints
     */
    public boolean isAlive(){
        if(currentHitpoints > 0)
            return true;

        return false;
    }

    /**
     * Adds a new Item to the player's inventory from a monster drop.
     * Accordingly updates amount if the item already exists
     */
    public void addDrop(Drop drop) {
        if (inventory.containsKey(drop.getItem().getName())) {
            int oldAmount = inventory.get(drop.getItem().getName()).getAmount();
            inventory.put(drop.getItem().getName(), new InventoryItem(drop.getItem(), oldAmount + drop.getAmount()));
        } else {
            inventory.put(drop.getItem().getName(), new InventoryItem(drop.getItem(), drop.getAmount()));
        }
    }

    /**
     * Prints out the players current stats to the console
     */
    public void displayStats(){
        System.out.println("========= Player Stats =========");
        System.out.println("Name: " + name);
        System.out.println("Health: " + currentHitpoints + "/" + maxHitpoints);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defence);
        System.out.println("Speed: " + speed);
        System.out.println("Experience: " + experience);
        System.out.println("================================");
    }

    /**
     * Prints out the players inventory to the console
     */
    public void displayInventory(){
        System.out.println("Player's Inventory:");
        Iterator it = inventory.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            InventoryItem entry = (InventoryItem) pair.getValue(); // convert the key to the actual item to read its name

            System.out.println(pair.getKey() + " : " + entry.getAmount());
        }
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
    public int getCurrentHitpoints() {
        return currentHitpoints;
    }
    public void setCurrentHitpoints(int hitpoints) {
        this.currentHitpoints = hitpoints;
    }
    public int getMaxHitpoints() {return maxHitpoints;}
    public void setMaxHitpoints(int maxHitpoints) { this.maxHitpoints = maxHitpoints; }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public HashMap<String, InventoryItem> getInventory() {
        return inventory;
    }
    public void setInventory(HashMap<String, InventoryItem> inventory) { this.inventory = inventory; }
}
