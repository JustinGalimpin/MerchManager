package model;

// Represents an item having a name, type, rarity, cost, and description
public class Item {
    private String itemName;
    private String type;
    private String rarity;
    private int cost;
    private String description;

    // REQUIRES: itemName is not an empty string, cost > 0
    // EFFECTS: constructs an with a given, type, rarity, cost, and description
    public Item(String itemName, String type, String rarity, int cost, String description) {
        this.itemName = itemName;
        this.type = type;
        this.rarity = rarity;
        this.cost = cost;
        this.description = description;
    }

    // Getters

    public String getItemName() {
        return this.itemName;
    }
    
    public String getType() {
        return this.type;
    }

    public String getRarity() {
        return this.rarity;
    }

    public int getCost() {
        return this.cost;
    }

    public String getDescription() {
        return this.description;
    }

}
