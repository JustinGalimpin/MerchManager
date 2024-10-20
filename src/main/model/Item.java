package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents an item having a name, type, quality, cost, and description
public class Item implements Writable {
    private String itemName;
    private String type;
    private String quality;
    private int price;
    private String description;

    // REQUIRES: itemName, type, quality, and description are not an empty string, price > 0
    // EFFECTS: constructs an item with a given, type, quality, price, and description
    public Item(String itemName, String type, String quality, int price, String description) {
        this.itemName = itemName;
        this.type = type;
        this.quality = quality;
        this.price = price;
        this.description = description;
    }

    // REQUIRES: newValue is a non-empty string
    // MODIFIES: this
    // EFFECTS: Updates the selected field of the item to the newValue
    public void updateItem(String field, String newValue) {
        switch (field) {
            case "name":
                setItemName(newValue);
                break;
            case "price":
                setPrice(Integer.parseInt(newValue));
                break;
            case "description":
                setDescription(newValue);
                break;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemName", itemName);
        json.put("type", type);
        json.put("quality", quality);
        json.put("price", price);
        json.put("description", description);
        return json;
    }

    // Getters and Settters

    public String getItemDetails() {
        return "Item Name: " + getItemName() + "\n" 
            +
            "Price: " + getPrice() + "\n" 
            +
            "Quality: " + getQuality() + "\n" 
            +
            "Description: " + getDescription();
    }

    public String getItemName() {
        return this.itemName;
    }
    
    public String getType() {
        return this.type;
    }

    public String getQuality() {
        return this.quality;
    }

    public int getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    // REQUIRES: newName is a non-empty string
    // MODIFIES: this
    // EFFECT: replaces the item's current name with a new one
    public void setItemName(String newName) {
        this.itemName = newName;
    }

    // REQUIRES: newPrice > 0
    // MODIFIES: this
    // EFFECT: replaces the item's current price with a new one
    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    // REQUIRES: newDescription is a non-empty string
    // MODIFIES: this
    // EFFECT: replaces the item's current description with a new one
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

}
