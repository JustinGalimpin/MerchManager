package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testSetItemName() {
        assertEquals("Sword", sword.getItemName());
        sword.setItemName("Fancy Sword");
        assertEquals("Fancy Sword", sword.getItemName());
    }

    @Test
    void testSetPrice() {
        assertEquals(10, sword.getPrice());
        sword.setPrice(20);
        assertEquals(20, sword.getPrice());
    }

    @Test
    void testSetDescription() {
        assertEquals("A basic sword.", sword.getDescription());
        sword.setDescription("A really basic sword.");
        assertEquals("A really basic sword.", sword.getDescription());
    }

}
