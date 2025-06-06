package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestShop {
    private Shop shop;
    private Item sword;
    private Item potion;

    @BeforeEach
    void runBefore() {
        shop = new Shop("Test Shop");
        sword = new Item("Sword", "Weapon", "Common", 10, "A basic sword.");
        potion = new Item("Health Potion", "Consumable", "Common", 50, "Cures your wounds.");
    }

    @Test
    void testConstructor() {
        assertEquals("Test Shop", shop.getShopName());
        assertEquals(0, shop.getIncome());
        assertEquals(0, shop.getInventorySize());
    }

    @Test
    void testAddItem() {
        shop.addItem(sword);
        assertEquals(1, shop.getInventorySize());
    }

    @Test
    void testAddItemWithSameName() {
        shop.addItem(sword);
        assertEquals(1, shop.getInventorySize());
        shop.addItem(sword);
        assertEquals(1, shop.getInventorySize());
    }

    @Test
    void testAddMultipleItems() {
        shop.addItem(sword);
        assertEquals(1, shop.getInventorySize());
        shop.addItem(potion);
        assertEquals(2, shop.getInventorySize());
    }

    @Test
    void testSellItem() {
        shop.addItem(sword);
        assertEquals(1, shop.getInventorySize());
        shop.removeItem(sword);
        assertEquals(0, shop.getInventorySize());
        assertEquals(10, shop.getIncome());
    }

    @Test
    void testSellMulltipleItem() {
        shop.addItem(sword);
        assertEquals(1, shop.getInventorySize());
        shop.addItem(potion);
        assertEquals(2, shop.getInventorySize());

        shop.removeItem(sword);
        assertEquals(1, shop.getInventorySize());
        assertEquals(10, shop.getIncome());
        shop.removeItem(potion);
        assertEquals(0, shop.getInventorySize());
        assertEquals(60, shop.getIncome());
    }

    @Test
    void testGetInventorySameInventory() {
        ArrayList<Item> expectedInventory = new ArrayList<>();
        expectedInventory.add(sword);
        expectedInventory.add(potion);

        shop.addItem(sword);
        shop.addItem(potion);
        assertEquals(expectedInventory, shop.getInventory());
    }

    @Test
    void testGetInventoryDiffInventory() {
        ArrayList<Item> expectedInventory = new ArrayList<>();
        expectedInventory.add(potion);
        expectedInventory.add(sword);

        shop.addItem(sword);
        shop.addItem(potion);
        assertNotEquals(expectedInventory, shop.getInventory());
    }

}
