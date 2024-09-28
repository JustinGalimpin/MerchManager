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
        //stub
    }

    // REQUIRES: 
    // MODIFIES: this
    // EFFECTS: adds item to inventory
    public void addItem(Item item) {
        //stub
    }

    // REQUIRES: inventory contains the given item
    // MODIFIES: this
    // EFFECTS: removes item from inventory,
    //          increases shop's income by item's cost
    public void sellItem(Item item) {
        //stub
    }

    // REQUIRES: inventory contains the given item
    // MODIFIES: this, Item
    // EFFECTS: changes the current value of a chosen quality of an item and
    //          replaces it with a new value
    public void modifyItem(Item item) {
        //stub
    }

    // EFFECTS: returns a printed list of all item names in inventory
    public void viewInventory() {
        //stub       
    }

    // Getters and Setters

    public String getShopName() {
        return shopName;
    }
    
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getIncome() {
        return income;
    }

    public void setShopName() {
        // stub
    }

    public void setIncome() {
        // stub
    }

}
