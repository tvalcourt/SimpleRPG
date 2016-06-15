package assets;

import assets.item.InventoryItem;
import assets.item.Item;
import util.LoadScript;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Trevor on 6/13/2016.
 */
public class Library{

    /**
     * Parses the returned Lua Table and converts it into an ArrayList where each
     * ArrayList contains a Drop: <Item Name> <Amount> <Drop Rate>
     * This list gets added to the enemies drop table
     * @param scriptLoader The script loader associated with the object
     * @param numEntries Number of entries in table
     * @param list List this data is read into
     */
    public static void readLuaTable(LoadScript scriptLoader, int numEntries, ArrayList list, boolean isNested, String flag){
            ArrayList<String> item = new ArrayList<>();
            String entry = "";
            int count = 0;

            scriptLoader.getLuaState().pushNil();

            while(scriptLoader.getLuaState().next(-2) != 0) {
                if (scriptLoader.getLuaState().isString(-1))
                    entry = scriptLoader.getLuaState().toString(-2) + " = " + scriptLoader.getLuaState().toString(-1);
                else if (scriptLoader.getLuaState().isNumber(-1))
                    entry = scriptLoader.getLuaState().toString(-2) + " = " + scriptLoader.getLuaState().toNumber(-1);
                else if (scriptLoader.getLuaState().isTable(-1))
                    readLuaTable(scriptLoader, numEntries, list, isNested, flag);
                else{
                    System.out.println("Error, aborting");
                    System.exit(1);
                }


                if (entry != "") {
                    item.add(entry);
                    count++;
                }

                if (count == numEntries) {
                    if(isNested) {
                        list.add(item);
                    } else {
                        switch(flag){
                            case "quest":
                                String formatAmt = item.get(1).substring(4, item.get(1).length());
                                String formatName = item.get(0).substring(4, item.get(0).length());

                                list.add(new InventoryItem(new Item(formatName), Integer.parseInt(formatAmt)));
                                break;
                        }
                    }
                    count = 0;
                }

                scriptLoader.getLuaState().pop(1);
            }
    }

    /**
     * Sorts and strips a nested array; comes from enemy item drops
     * @param list The unsorted list of data
     */
    public static void sortAndStripNestedArray(ArrayList<ArrayList<String>> list){
        for (ArrayList<String> item : list)
            item.sort((item_1, item_2) -> item_1.compareTo(item_2));

        /** Parse each item and remove leading garbage */
        for(ArrayList<String> item : list) {
            for (String entry : item){
                // Entries in format: A = XXXX -> XXXX
                int itemIndex = item.indexOf(entry);
                item.set(itemIndex, entry.substring(4, entry.length()));
            }
        }
    }

    /**
     * Formats an array of Inventory Items to get rid of leading XXXX
     * @param list List of items to format
     */
    public static void formatInventoryItemArray(ArrayList<InventoryItem> list){
        for(InventoryItem item : list){
            String itemName = item.getItem().getName();
            item.getItem().setName(itemName.substring(4, itemName.length()));
        }
    }
}
