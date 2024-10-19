package persistence;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Shop;

// Represents a reader that reads shop from JSON data stored in 
// Code for the following classs is heavily attributed to the JsonSerializationDemo example on edX
public class JsonReader {
    
   // EFFECTS: constructs reader to read from source file
   public JsonReader(String source) {

   }

    // EFFECTS: reads shop from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Shop read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;

    }

    // EFFECTS: parses shop from JSON object and returns it
    private Shop parseShop(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: shop
    // EFFECTS: parses items from JSON object and adds them to shop
    private void addItems(Shop shop, JSONObject jsonObject) {
        
    }

    // MODIFIES: shop
    // EFFECTS: parses item from JSON object and adds it to workroom
    private void addItem(Shop shop, JSONObject jsonObject) {
        
    }


}
