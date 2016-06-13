package entity.enemy;

import main.SimpleRPG;
import util.LoadScript;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generic enemy class that calls the script for the given enemy type
 * Created by Trevor on 6/11/2016.
 */
public class Enemy {
    protected String name, race;
    protected int attack, defence, speed, hitpoints, experience;
    protected ArrayList<ArrayList<String>> drops;
    private LoadScript scriptLoader;

    /**
     * Constructor for any Enemy
     *
     * @param name Name of the enemy
     * @param race Race of the enemy
     */
    public Enemy(String name, String race) {
        this.name = name;
        this.race = race;
        drops = new ArrayList();

        this.scriptLoader = new LoadScript("src/entity/enemy/" + name.toLowerCase() + ".lua");
        this.scriptLoader.runScriptFunction("create", this, 1, 0);

        // Setup Drop Table
        this.scriptLoader.getLuaState().getGlobal("drops");

        /** Load drop table for given script */
        /** Sort the data in each item entry since they are read in a inconsistent order from Lua */
        buildDropList();
        for (ArrayList<String> item : drops)
            item.sort((item_1, item_2) -> item_1.compareTo(item_2));

        /** Parse each item and remove leading garbage */
        for(ArrayList<String> item : drops) {
            for (String entry : item){
                // Entries in format: A = XXXX -> XXXX
                int itemIndex = item.indexOf(entry);
                item.set(itemIndex, entry.substring(4, entry.length()));
            }
        }
    }

    /**
     * Parses the returned Lua Table and converts it into an ArrayList where each
     * ArrayList contains a Drop: <Item Name> <Amount> <Drop Rate>
     * This list gets added to the enemies drop table
     */
    public void buildDropList(){
        ArrayList<String> item = new ArrayList<>();
        String entry = "";
        int count = 0;
        this.scriptLoader.getLuaState().pushNil();

        while(this.scriptLoader.getLuaState().next(-2) != 0){
            if(this.scriptLoader.getLuaState().isString(-1))
                entry = this.scriptLoader.getLuaState().toString(-2) + " = " + this.scriptLoader.getLuaState().toString(-1);
            else if(this.scriptLoader.getLuaState().isNumber(-1))
                entry = this.scriptLoader.getLuaState().toString(-2) + " = " + this.scriptLoader.getLuaState().toNumber(-1);
            else if(this.scriptLoader.getLuaState().isTable(-1))
                buildDropList();

            if(entry != "") {
                item.add(entry);
                count++;
            }

            if(count == 3) {
                drops.add(item);
                count = 0;
            }

            this.scriptLoader.getLuaState().pop(1);
        }
    }

    /**
     * Determine if the player is alive based on hitpoints
     */
    public boolean isAlive(){
        if(hitpoints > 0)
            return true;

        return false;
    }

    /**
     * When the enemy is defeated in battle, award the player
     * with EXP and roll for any drops to add to the inventory
     */
    public void defeated(){
        System.out.println("Player has defeated the " + name);

        // Check for drops
        Random generator = new Random();
        for(ArrayList<String> drop : drops){

            if(generator.nextInt(99) + 1 <= Integer.parseInt(drop.get(2))){
                String[] itm = new String[2];
                itm[0] = drop.get(0);
                itm[1] = drop.get(1);

                SimpleRPG.getPlayer().getInventory().add(itm);
            }
        }
        SimpleRPG.getPlayer().displayInventory();

        // Add experience
        SimpleRPG.getPlayer().setExperience(
                SimpleRPG.getPlayer().getExperience() + this.experience);

        System.out.println("Player's Experience: " + SimpleRPG.getPlayer().getExperience());

        scriptLoader.getLuaState().close();
    }

    /**
     * Prints out the players current stats to the console
     */
    public void displayStats(){
        System.out.println("========= Enemy Stats =========");
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
    public String getName() {return name;}
    public String getRace() {
        return race;
    }
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
    public int getSpeed() {return speed;}
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
    public ArrayList<ArrayList<String>> getDrops() {
        return drops;
    }
    public void setDrops(ArrayList<ArrayList<String>> drops) {
        this.drops = drops;
    }
}
