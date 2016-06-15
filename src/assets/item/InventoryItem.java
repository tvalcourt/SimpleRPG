package assets.item;

import assets.item.Item;

/**
 * Created by Trevor on 6/12/2016.
 */
public class InventoryItem {

    Item item;
    int amount;

    public InventoryItem(Item item, int amount){
        this.item = item;
        this.amount = amount;
    }

    public Item getItem(){
        return this.item;
    }
    public int getAmount(){
        return this.amount;
    }
}
