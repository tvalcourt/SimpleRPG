package assets.quest;

import assets.Library;
import assets.item.InventoryItem;
import assets.item.Item;
import util.LoadScript;

import java.util.ArrayList;

/**
 * Represents a quest that the player can take
 * Created by Trevor on 6/13/2016.
 */
public class Quest {

    /**
     * Players journal:
     * > Active Quests
     * > Completed Quests
     *
     * Quests should know if:
     *  > They are available
     *  > They are active
     *  > They are completed
     * @param name
     */

    protected boolean available, active, completed;
    private ArrayList<InventoryItem> itemReward;
    protected int experienceReward;
    private LoadScript scriptLoader;

    /**
     * Constructor
     * @param name Name of the quest
     */
    public Quest(String name){
        itemReward = new ArrayList<>();

        System.out.println(name);
        scriptLoader = new LoadScript("src/assets/quest/script/" + name + ".lua"); // no whitespace
        scriptLoader.runScriptFunction("init", this, 1, 0);

        // Build rewards list
        this.scriptLoader.getLuaState().getGlobal("item_rewards");
        Library.readLuaTable(this.scriptLoader, 2, itemReward, false, "quest");
        displayItemReward();
    }

    public void displayItemReward(){
        for(InventoryItem item : itemReward)
            System.out.println("ITEM: " + item.getItem().getName() +"\tAMT: " + item.getAmount());
    }

    /** Accessors and Mutators */
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public int getExperienceReward() {
        return experienceReward;
    }
    public void setExperienceReward(int experienceReward) {
        this.experienceReward = experienceReward;
    }
}
