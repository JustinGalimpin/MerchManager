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
        assertEquals("Common", sword.getRarity());
        assertEquals(10, sword.getPrice());
        assertEquals("A basic sword.", sword.getDescription());
    }

    

}
