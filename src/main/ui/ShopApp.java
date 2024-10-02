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
        if (command.equals("a")) {
            addNewItem();
        } else if (command.equals("s")) {
            sellAnItem();
        } else if (command.equals("m")) {
            searchForItemToMod();
        } else if (command.equals("l")) {
            viewInventory();
        } else if (command.equals("d")) {
            viewItemDetails();
        } else if (command.equals("$")) {
            System.out.println("Total Income: " + firstShop.getIncome());
        } else {
            System.out.println("Selection not valid; try again!");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to the Shop! What would you like to do?");
        System.out.println("\ta -> Add an item to the Shop");
        System.out.println("\ts -> Sell an item from the Shop");
        System.out.println("\tm -> Modify an item from the Shop");
        System.out.println("\tl -> View the list of items in the Shop");
        System.out.println("\td -> View the details of a specific item");
        System.out.println("\t$ -> View the Shop's total income");
        System.out.println("\tq -> Leave the Shop");
    }

    // REQUIRES: itemName, type, quality, price, and and description
    //           are non-empty Strings (or int in the case of price)
    //           itemName is not one that already exists in the inventory
    // MODIFIES: this, inventory
    // EFFECTS: processes user input for a new item and
    //          adds the item to the Shop's inventory
    @SuppressWarnings("methodlength")
    public void addNewItem() {
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

    // REQUIRES: Item name exists in the Shop
    // MODIFIES: this, inventory
    // EFFECTS: searches for an item in the inventory, sells (removes) the item
    //          and adds its price to the shops income
    public void sellAnItem() {
        System.out.println("What is the name of the item you want to buy?");
        String itemToBuy = scanner.nextLine();

        ArrayList<Item> inventory = firstShop.getInventory();
        
        for (Item item : inventory) {
            if (item.getItemName().equals(itemToBuy)) {
                System.out.println(item.getItemName() + " has been sold!");
                firstShop.sellItem(item);
                break;
            }
        }      
    }

    // REQUIRES: inventory is not empty, item exists in inventory
    // MODIFIES: this, item
    // EFFECTS: searches for an item in the inventory to be modified
    public void searchForItemToMod() {
        System.out.println("What is the name of the item you want to modify?");
        String itemToModify = scanner.nextLine();

        ArrayList<Item> inventory = firstShop.getInventory();

        for (Item item : inventory) {
            if (item.getItemName().equals(itemToModify)) {
                modifyAnItem(item);
            }
        }          
    }

    // REQUIRES: item exists in inventory (if the inventory is not empty)
    // EFFECTS: search for item in inventory, then
    //          prints out all qualities of the given item
    public void viewItemDetails() {
        ArrayList<Item> inventory = firstShop.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("The shop has nothing to view!");
        } else {
            System.out.println("What is the name of the item you want to view?");
            String itemToView = scanner.nextLine();

            for (Item item : inventory) {
                if (item.getItemName().equals(itemToView)) {
                    System.out.println("Item Name: " + item.getItemName());
                    System.out.println("Price: " + item.getPrice());
                    System.out.println("Quality: " + item.getQuality());
                    System.out.println("Description: " + item.getDescription());
                }
            }        
        }
    }

    // REQUIRES: newName, newPrice, and/or newDescription are non-empty
    // MODIFIES: this, item
    // EFFECT: Replaces the item's current name, price, or description
    //         with a new name, item, or description
    public void modifyAnItem(Item item) {
        System.out.println("Do you want to change the name, price, or description?");
        String modifyCommand = scanner.nextLine();
        if (modifyCommand.equals("name")) {
            System.out.println("Please enter new name:");
            String newName = scanner.nextLine();
            item.setItemName(newName);
            System.out.println("Item is now named " + item.getItemName() + "!");
        } else if (modifyCommand.equals("price")) {
            System.out.println("Please enter new price:");
            String newPrice = scanner.nextLine();
            item.setPrice(Integer.valueOf(newPrice));
            System.out.println(item.getItemName() + " now costs " + item.getPrice() + "!");
        } else if (modifyCommand.equals("description")) {
            System.out.println("Please enter new description:");
            String newDescription = scanner.nextLine();
            item.setDescription(newDescription);
            System.out.println("New description for " + item.getItemName() + ": " + item.getDescription());
        } else { 
            System.out.println("Option not valid, try again!");
            modifyAnItem(item);
        }
    }

    // EFFECT: Prints out the names of all items in the Shop,
    //         otherwise, tells the user the Shop is empty
    public void viewInventory() {
        ArrayList<Item> inventory = firstShop.getInventory();

        if (inventory.isEmpty()) {
            System.out.println("The shop has nothing for sale!");
        } else {
            for (Item item : inventory) {
                System.out.println("Item for sale: " + item.getItemName() + " for " + item.getPrice() + " gold.");
            }      
        }
    }
}


