package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a shop having a name, inventory, and total money earned
public class Shop implements Writable {
    private String shopName;
    private ArrayList<Item> inventory;
    private int income;

    // REQUIRES: shopName is not an empty string
    // EFFECTS: constructs a shop with given title, 0 income, and an 
    //          empty inventory
    public Shop(String shopName) {
        this.shopName = shopName;
        this.inventory = new ArrayList<>();
        this.income = 0;
    }

    // REQUIRES: itemName is not an empty string
    // MODIFIES: this
    // EFFECTS: checks if an item with the same name alrady exists,
    //          if it does, returns false, otherwise returns true and adds item to inventory
    public boolean addItem(Item item) {
        boolean exists = false;
        for (Item i : inventory) {
            if (i.getItemName().equals(item.getItemName())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            inventory.add(item);
            EventLog.getInstance().logEvent(new Event("Item added to inventory: " + item.getItemName()));
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: inventory contains item
    // MODIFIES: this
    // EFFECTS: removes item from inventory,
    //          increases shop's income by item's cost
    public void removeItem(Item item) {
        this.income = this.income + item.getPrice();
        inventory.remove(item);
        EventLog.getInstance().logEvent(new Event("Item sold from inventory: " + item.getItemName()));
        EventLog.getInstance().logEvent(new Event("Shop income increased by: " + item.getPrice()));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", shopName);
        json.put("items", itemsToJson());
        json.put("income", income);
        return json;
    }

        // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item item : inventory) {
            jsonArray.put(item.toJson());
        }

        return jsonArray;
    }

    public String getShopName() {
        return shopName;
    }
    
    public int getInventorySize() {
        return inventory.size();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = this.income + income;
    }
}
