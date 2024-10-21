package persistence;

import org.junit.jupiter.api.Test;

import model.Item;
import model.Shop;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestJsonWriter extends TestJson {

    @Test
    void testWriterInvalidFile() {
        try {
            Shop shop = new Shop("My Test Shop");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyShop() {
        try {
            Shop shop = new Shop("My Test Shop");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShop.json");
            writer.open();
            writer.write(shop);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShop.json");
            shop = reader.read();
            assertEquals("My Test Shop", shop.getShopName());
            assertEquals(0, shop.getInventorySize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShop() {
        try {
            Shop shop = new Shop("My Test Shop");
            shop.addItem(new Item("Potion", "Consumable", "High", 50, "A potion to cure your injuries."));
            shop.addItem(new Item("Sword", "Weapon", "Good", 20, "A good sword."));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShop.json");
            writer.open();
            writer.write(shop);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShop.json");
            shop = reader.read();
            assertEquals("My Test Shop", shop.getShopName());
            List<Item> items = shop.getInventory();
            assertEquals(2, items.size());
            checkItem("Potion", "Consumable", "High", 50, "A potion to cure your injuries.", items.get(0));
            checkItem("Sword", "Weapon", "Good", 20, "A good sword.", items.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}