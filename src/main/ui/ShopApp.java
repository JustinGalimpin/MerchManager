package ui;

import model.Item;
import model.Shop;

import java.util.ArrayList;
import java.util.Scanner;

// Shop application
// Code for the following methods are attributed to the TellerApp example on edX:
// runShop(), processCommand(), displayMenu()

public class ShopApp {
    private Shop firstShop;
    private Scanner scanner;

    // EFFECTS: Runs the shop application
    public ShopApp() {
        runShop();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runShop() {
        boolean keepGoing = true;
        String command = null;
        init();
        while (keepGoing) {
            displayMenu();
            command = scanner.nextLine();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for visiting!");
    }

    // MODIFIES: this
    // EFFECTS: initializes Shop
    private void init() {
        firstShop = new Shop("The First Shop");
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\r?\n|\r");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "a": addNewItem();
                break;
            case "s": sellItem();
                break;
            case "m": searchForItemToMod();
                break;
            case "l": viewInventory();
                break;
            case "d": viewItemDetails();
                break;
            case "$": System.out.println("Total Income: $" + firstShop.getIncome());
                break;
            case "q":
                break;
            default: System.out.println("Selection not valid; try again!");
                break;
        }   
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to " + firstShop.getShopName() + "! What would you like to do?");
        System.out.println("\ta -> Add an item to the Shop");
        System.out.println("\ts -> Sell an item from the Shop");
        System.out.println("\tm -> Modify an item from the Shop");
        System.out.println("\tl -> View the list of items in the Shop");
        System.out.println("\td -> View the details of a specific item");
        System.out.println("\t$ -> View the Shop's total income");
        System.out.println("\tq -> Leave the Shop");
    }

    // REQUIRES: itemName, type, quality, and description are non-empty Strings
    //           itemName is not one that already exists in the inventory
    //           price > 0 
    // MODIFIES: this, item
    // EFFECTS: processes user input for a new item and
    //          adds the item to the Shop's inventory
    private void addNewItem() {
        System.out.println("Enter item name:");
        String itemName = scanner.nextLine();
        System.out.println("Enter item type:");
        String type = scanner.nextLine();
        System.out.println("Enter item quality:");
        String quality = scanner.nextLine();
        System.out.println("Enter item price (integer value):");
        int price = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter item description:");
        String description = scanner.nextLine();
        Item newItem = new Item(itemName, type, quality, price, description);
        if (firstShop.addItem(newItem)) {
            System.out.println("Item has been added to the shop!");
        } else {
            System.out.println("Error! The shop already has an item with that name.");
        }
    }

    // REQUIRES: item exists in inventory
    // MODIFIES: this
    // EFFECTS: searches for an item in the inventory, sells (removes) the item
    //          and adds its price to the shops income
    private void sellItem() {
        System.out.println("What is the name of the item you want to sell?");
        String itemToBuy = scanner.nextLine();
        ArrayList<Item> inventory = firstShop.getInventory();
        for (Item item : inventory) {
            if (item.getItemName().equals(itemToBuy)) {
                System.out.println(item.getItemName() + " has been sold!");
                firstShop.removeItem(item);
                break;
            }
        }      
    }

    // EFFECTS: searches for an item in the inventory to be modified
    private void searchForItemToMod() {
        System.out.println("What is the name of the item you want to modify?");
        String itemToModify = scanner.nextLine();
        ArrayList<Item> inventory = firstShop.getInventory();
        for (Item item : inventory) {
            if (item.getItemName().equals(itemToModify)) {
                requestItemField(item);
                return;
            }
        }    
        System.out.println("Item not found in inventory!");      
    }

    // REQUIRES: newName, newPrice, and/or newDescription are non-empty
    // EFFECT: prompts the user for which field to modify
    //         tells the user if the change was successful or not
    private void requestItemField(Item item) {
        System.out.println("Do you want to change the name, price, or description?");
        String modifyCommand = scanner.nextLine().toLowerCase();
        if (modifyCommand.equals("name") || modifyCommand.equals("price") || modifyCommand.equals("description")) {
            System.out.println("Enter the new value for " + modifyCommand + ":");
            String newValue = scanner.nextLine();
            item.updateItem(modifyCommand, newValue);
            System.out.println(modifyCommand + " has been updated!");
        } else {
            System.out.println("Option not valid, try again!");
            requestItemField(item);
        }
    }

    // REQUIRES: item exists in inventory (if the inventory is not empty)
    // EFFECTS: search for item in inventory, then
    //          prints out all qualities of the given item
    private void viewItemDetails() {
        ArrayList<Item> inventory = firstShop.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("The shop has nothing to view!");
        } else {
            System.out.println("What is the name of the item you want to view?");
            String itemToView = scanner.nextLine();
            for (Item item : inventory) {
                if (item.getItemName().equals(itemToView)) {
                    System.out.println(item.getItemDetails());
                    break;
                }
            }        
            System.out.println("Item does not exist!");
        }
    }

    // EFFECT: Prints out the names of all items in the Shop,
    //         otherwise, tells the user the Shop is empty
    private void viewInventory() {
        ArrayList<Item> inventory = firstShop.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("The shop has nothing for sale!");
        } else {
            for (Item item : inventory) {
                System.out.println("Item for sale: " + item.getItemName() + " for $" + item.getPrice());
            }      
        }
    }
}


