package model;

// Represents an item having a name, type, rarity, cost, and description
public class Item {
    private String itemName;
    private String type;
    private String rarity;
    private int price;
    private String description;

    // REQUIRES: itemName is not an empty string, cost > 0
    // EFFECTS: constructs an with a given, type, rarity, cost, and description
    public Item(String itemName, String type, String rarity, int price, String description) {
        this.itemName = itemName;
        this.type = type;
        this.rarity = rarity;
        this.price = price;
        this.description = description;
    }

    // Getters and Settters

    public String getItemName() {
        return this.itemName;
    }
    
    public String getType() {
        return this.type;
    }

    public String getRarity() {
        return this.rarity;
    }

    public int getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    // MODIFIES: this
    // EFFECT: replaces the item's current name with a new one
    public void setItemName(String newName) {
        this.itemName = newName;
    }

    // MODIFIES: this
    // EFFECT: replaces the item's current price with a new one
    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    // MODIFIES: this
    // EFFECT: replaces the item's current description with a new one
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

}
