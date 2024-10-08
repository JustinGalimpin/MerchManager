package model;

// Represents an item having a name, type, quality, cost, and description
public class Item {
    private String itemName;
    private String type;
    private String quality;
    private int price;
    private String description;

    // REQUIRES: itemName, type, quality, and description are not an empty string, price > 0
    // EFFECTS: constructs an with a given, type, quality, price, and description
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
    public void updateItemField(String field, String newValue) {
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

    // Getters and Settters

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
