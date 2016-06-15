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

    public static enum QuestStatus {UNAVAILABLE, AVAILABLE, ACTIVE, COMPLETED}; // represents states of the quest

    protected String name;
    protected QuestStatus status;
    private ArrayList<InventoryItem> itemReward;
    protected int experienceReward;
    private LoadScript scriptLoader;

    /**
     * Constructor
     * @param name Name of the quest
     */
    public Quest(String name){
        itemReward = new ArrayList<>();
        this.name = name;

        // Load the quest script
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
    public String getStatus(){
        switch(status) {
            case UNAVAILABLE:
                return "Unavailable";
            case AVAILABLE:
                return "Available";
            case ACTIVE:
                return "Active";
            case COMPLETED:
                return "Completed";
        }
        return "Unknown status";
    }
    public void setStatus(QuestStatus status){
        this.status = status;
    }
    public void setLuaStatus(String status){
        switch(status) {
            case "unavailable":
                this.status = QuestStatus.UNAVAILABLE;
                break;

            default:
                this.status = QuestStatus.AVAILABLE;
                break;
        }
    }
    public String getName(){
        return this.name;
    }
    public int getExperienceReward() {
        return experienceReward;
    }
    public void setExperienceReward(int experienceReward) {
        this.experienceReward = experienceReward;
    }
}
