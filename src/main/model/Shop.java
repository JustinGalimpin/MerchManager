package model;

import java.util.ArrayList;

// Represents a shop having a name, inventory, and total money earned
public class Shop {
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

    // MODIFIES: this
    // EFFECTS: checks if an item with the same name alrady exists,
    //          if it does, it fails to add the new item to the inventory
    //          otherwise, it adds the new item to inventory
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
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: inventory contains the given item
    // MODIFIES: this
    // EFFECTS: removes item from inventory,
    //          increases shop's income by item's cost
    public void sellItem(Item item) {
        this.income = this.income + item.getPrice();
        inventory.remove(item);
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

}
