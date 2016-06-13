package assets.item;

import assets.Item;

/**
 * Basic Potion. Restores 10 HP to the player.
 * Created by Trevor on 6/12/2016.
 */
public class Potion extends Item {

    /**
     * Constructor
     * @param name Name of the item
     */
    public Potion(String name){
        super(name);
        super.setDescription("Restores 10 HP to the player.");
    }
}
