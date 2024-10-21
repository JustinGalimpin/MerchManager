package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Item;
import model.Shop;
import persistence.JsonReader;

public class TestJsonReader extends TestJson {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Shop shop = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShop() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyShop.json");
        try {
            Shop shop = reader.read();
            assertEquals("My Test Shop", shop.getShopName());
            assertEquals(0, shop.getInventorySize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralShop.json");
        try {
            Shop shop = reader.read();
            assertEquals("My Test Shop", shop.getShopName());
            List<Item> items = shop.getInventory();
            assertEquals(2, items.size());
            checkItem("Sword", "Weapon", "Good", 20, "A good sword.", items.get(0));
            checkItem("Potion", "Consumable", "High", 50, "A potion to cure your injuries.", items.get(1));
            // checkItem();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}