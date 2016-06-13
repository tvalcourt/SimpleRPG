package assets;

/**
 * Represents a Drop table entry for a given item
 * Created by Trevor on 6/12/2016.
 */
public class Drop {
    Item item;
    int amount;
    double rate;

    /**
     Constructor
     @param item The item which will be dropped by an enemy
     @param amount The number of items dropped
     @param rate The drop rate from 0.0 to 100.0
     */
    public Drop(Item item, int amount, double rate){
        this.item = item;
        this.amount = amount;
        this.rate = rate;
    }

    /** Accessors for Drop data */
    public Item getItem(){
        return this.item;
    }
    public int getAmount(){
        return this.amount;
    }
    public double getRate(){
        return this.rate;
    }
}
