package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestItem {

    private Item sword;

    @BeforeEach
    void runBefore() {
        sword = new Item("Sword", "Weapon", "Common", 10, "A basic sword.");
    }

    @Test
    void testConstructor() {
        assertEquals("Sword", sword.getItemName());
        assertEquals("Weapon", sword.getType());
        assertEquals("Common", sword.getQuality());
        assertEquals(10, sword.getPrice());
        assertEquals("A basic sword.", sword.getDescription());
    }

    @Test
    void testGetItemDetails() {
        String expectedDetails = "Item Name: Sword\n" +
                                 "Price: 10\n" +
                                 "Type: Weapon\n" +
                                 "Quality: Common\n" +
                                 "Description: A basic sword.";
        assertEquals(expectedDetails, sword.getItemDetails());
    }

    @Test
    void testUpdateItemFieldName() {
        sword.updateItem("name", "Fancy Sword");
        assertEquals("Fancy Sword", sword.getItemName());

        // Assert nothing else has been changed
        assertEquals("Weapon", sword.getType());
        assertEquals("Common", sword.getQuality());
        assertEquals(10, sword.getPrice());
        assertEquals("A basic sword.", sword.getDescription());
    }

    @Test
    void testUpdateItemFieldPrice() {
        sword.updateItem("price", "100");
        assertEquals(100, sword.getPrice());
        // Assert nothing else has been changed
        assertEquals("Sword", sword.getItemName());
        assertEquals("Weapon", sword.getType());
        assertEquals("Common", sword.getQuality());
        assertEquals("A basic sword.", sword.getDescription());
    }

    @Test
    void testUpdateItemFieldPriceOne() {
        sword.updateItem("price", "1");
        assertEquals(1, sword.getPrice());
    }

    @Test
    void testUpdateItemFieldDescription() {
        sword.updateItem("description", "A nice sword.");
        assertEquals("A nice sword.", sword.getDescription());
        // Assert nothing else has been changed
        assertEquals("Sword", sword.getItemName());
        assertEquals("Weapon", sword.getType());
        assertEquals(10, sword.getPrice());
        assertEquals("Common", sword.getQuality());
    }

    @Test
    void testInvalidField(){
        try {
            sword.updateItem("quality", "Legendary");
            sword.updateItem("type", "Shield");
            fail();
        } catch (IllegalArgumentException e) {
            // Assert nothing else has been changed
            assertEquals("A basic sword.", sword.getDescription());
            assertEquals("Sword", sword.getItemName());
            assertEquals("Weapon", sword.getType());
            assertEquals(10, sword.getPrice());
            assertEquals("Common", sword.getQuality());
        }
    }

}
