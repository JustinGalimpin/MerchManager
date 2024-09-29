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

    // REQUIRES: 
    // MODIFIES: this
    // EFFECTS: adds item to inventory
    public void addItem(Item item) {
        inventory.add(item);
    }

    // REQUIRES: inventory contains the given item
    // MODIFIES: this
    // EFFECTS: removes item from inventory,
    //          increases shop's income by item's cost
    public void sellItem(Item item) {
        this.income = this.income + item.getPrice();
        inventory.remove(item);
    }

    // REQUIRES: inventory contains the given item
    // MODIFIES: this, Item
    // EFFECTS: changes the current name of a chosen item and
    //          replaces it with a new name
    public void modifyName(Item item) {
        //stub
        // Do this as a singular method or multiple methods? 
        // Singular method = possibly use a switch case?
    }

    // REQUIRES: inventory contains the given item
    // MODIFIES: this, Item
    // EFFECTS: changes the current price of a chosen item and
    //          replaces it with a new price
    public void modifyPrice(Item item) {
        //stub
        // Do this as a singular method or multiple methods? 
        // Singular method = possibly use a switch case?
    }

    // REQUIRES: inventory contains the given item
    // MODIFIES: this, Item
    // EFFECTS: changes the current description of a chosen item and
    //          replaces it with a new description
    public void modifyDescription(Item item) {
        //stub
        // Do this as a singular method or multiple methods? 
        // Singular method = possibly use a switch case?
    }

    // EFFECTS: prints out a list of all item names in inventory
    //          tells the user if there are no items in the inventory
    public void viewInventory(ArrayList<Item> inventory) {
        for (Item item : inventory) {
            System.out.println(item);
        }
    }

    // Getters and Setters

    public String getShopName() {
        return shopName;
    }
    
    public int getInventorySize() {
        return inventory.size();
    }

    public int getIncome() {
        return income;
    }

}
