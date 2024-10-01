package ui;

import model.Item;
import model.Shop;

import java.util.ArrayList;
import java.util.Scanner;

// Shop application
// Some code for the following methods are attributed to the TellerApp example on edX:
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
            System.out.println("Filler"); // CHANGE
        } else if (command.equals("m")) {
            System.out.println("Filler"); // CHANGE
        } else if (command.equals("v")) {
            viewInventory();
        } else if (command.equals("$")) {
            firstShop.getIncome();
        } else {
            System.out.println("Selection not valid; try again!");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\ta -> Add an item to the Shop");
        System.out.println("\ts -> Sell an item from the Shop");
        System.out.println("\tm -> Modify an item from the Shop");
        System.out.println("\tv -> View the list of items in the Shop");
        System.out.println("\t$ -> View the Shop's total income");
        System.out.println("\tq -> Leave the Shop");
    }

    // REQUIRES: itemName, type, rarity, price, and and description
    //           are non-empty Strings (or int in the case of price)
    // MODIFIES: this, inventory
    // EFFECTS: processes user input for a new item and
    //          adds the item to the Shop's inventory
    @SuppressWarnings("methodlength")
    public void addNewItem() {
        System.out.println("Enter item name:");
        String itemName = scanner.nextLine();

        System.out.println("Enter item type:");
        String type = scanner.nextLine();

        System.out.println("Enter item rarity:");
        String rarity = scanner.nextLine();

        System.out.println("Enter item price (integer value):");
        int price = scanner.nextInt();
        scanner.nextLine(); // gets the remaining newline character

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        Item newItem = new Item(itemName, type, rarity, price, description);
        firstShop.addItem(newItem);

        System.out.println(itemName + " has been added to the shop!");
    }

    // MODIFIES: this, inventory
    // EFFECTS: searches for an item in the inventory,
    //          if the item exists, sells (removes) the item and
    //          adds its price to the shops income
    public void sellAnItem() {

    }

    public void modifyAnItem() {

    }

    // EFFECT: Prints out the names of all items in the Shop,
    //         otherwise, tells the user the Shop is empty
    public String viewInventory() {
        ArrayList<Item> inventory = firstShop.getInventory();

        if (inventory.isEmpty()) {
            System.out.println("The shop has nothing for sale!");
        } else {
            for (Item item : inventory) {
                System.out.println(item.getItemName());
            }      
        }

        return "All items have been listed.";
    }
}


