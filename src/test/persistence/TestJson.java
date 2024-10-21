package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Item;

public class TestJson {
    protected void checkItem(String itemName, String type, String quality, int price, String description, Item item) {
        assertEquals(itemName, item.getItemName());
        assertEquals(type, item.getType());
        assertEquals(quality, item.getQuality());
        assertEquals(price, item.getPrice());
        assertEquals(description, item.getDescription());
    }
}
