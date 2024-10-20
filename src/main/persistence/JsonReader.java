package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import model.Item;
import model.Shop;

// Represents a reader that reads shop from JSON data stored in 
// Code for the following class is heavily attributed to the JsonSerializationDemo example on edX
public class JsonReader {
    private String source;
    
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads shop from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Shop read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShop(jsonObject);    
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shop from JSON object and returns it
    private Shop parseShop(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Shop shop = new Shop(name);
        addItems(shop, jsonObject);
        return shop;    
    }

    // MODIFIES: shop
    // EFFECTS: parses items from JSON object and adds them to shop
    private void addItems(Shop shop, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(shop, nextItem);
        }
    }

    // MODIFIES: shop
    // EFFECTS: parses item from JSON object and adds it to workroom
    private void addItem(Shop shop, JSONObject jsonObject) {
        String itemName = jsonObject.getString("itemName");
        String type = jsonObject.getString("type");
        String quality = jsonObject.getString("quality");
        Integer price = jsonObject.getInt("price");
        String description = jsonObject.getString("description");

        Item item = new Item(itemName, type, quality, price, description);
        shop.addItem(item);        
    }


}
