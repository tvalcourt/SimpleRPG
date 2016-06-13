package assets;

import util.LoadScript;

/**
 * Abstract Item class to be used as a mold for all Items
 * Created by Trevor on 6/12/2016.
 */
public class Item {

    String name, description;
    LoadScript script;

    /**
     Constructor
     @param name The name of the item. Also is the name of the associated Lua Script
     */
    public Item(String name){
        this.name = name;
        script = new LoadScript(name + ".lua");
    }

    /**
     * Occurs when the player selects the "Use" function on this item
     * Passes the player in as a parameter
     */
    public void useItem(){
        script.runScriptFunction("use", this, 1, 0);
    }

    /** Accessors */
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String desc){
        this.description = desc;
    }
    public LoadScript getLoadScript(){
        return this.script;
    }

}
