package ui;

import javax.swing.*;

import model.Item;
import model.Shop;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Code for the following class is heavily attributed to the AlarmSystem Demo example on edX
public class ShopAppGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/shop.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Shop shop;
    private JTextArea outputArea; // Area to display output
    JTextArea itemListArea; // Area to display items

    // Constructor representing the GUI of a Shop Class
    public ShopAppGUI(Shop shop) {
        this.shop = shop;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Shop Inventory Management");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addButtonPanel();
        centreOnScreen();
        addOutputArea();
        addItemListArea();
        updateItemList();        
        setVisible(true);
        welcomePopUp();
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(new JButton(new AddItemAction()));
        buttonPanel.add(new JButton(new SellItemAction()));
        buttonPanel.add(new JButton(new ViewItemAction()));
        buttonPanel.add(new JButton(new ViewInventoryAction()));
        buttonPanel.add(new JButton(new SaveShopAction()));
        buttonPanel.add(new JButton(new LoadShopAction()));
        buttonPanel.add(new JButton(new ClearOutputAction()));
        buttonPanel.setPreferredSize(new Dimension(250, 300));
        add(buttonPanel, BorderLayout.WEST);
    }

    // EFFECTS: Centers main application window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // EFFECTS: Creates a center TextArea for application outputs
    private void addOutputArea() {
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(Color.LIGHT_GRAY);
        outputArea.setFont(new Font("Arial", Font.BOLD, 12)); 
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    // EFFECTS: Centers a persistent list for viewing items in the Shop
    private void addItemListArea() {
        itemListArea = new JTextArea();
        itemListArea.setEditable(false);
        itemListArea.setFont(new Font("Arial", Font.BOLD, 12)); 
        itemListArea.setPreferredSize(new Dimension(200, 500));
        add(new JScrollPane(itemListArea), BorderLayout.EAST); 
    }

    // EFFECTS: Shows a messageDialog with an image welcoming the user
    private void welcomePopUp() {
        ImageIcon soldIcon = new ImageIcon("./data/welcome.png");
        Image scaledIcon = soldIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledIcon);

        JLabel iconLabel = new JLabel(resizedIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        JLabel messageLabel = new JLabel("Welcome to the shop!");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(iconLabel);       
        panel.add(messageLabel);    

        JOptionPane.showMessageDialog(null, panel, "Welcome Message", JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: Updates the itemListArea to show the names of items in the Shop
    private void updateItemList() {
        StringBuilder itemList = new StringBuilder();
        ArrayList<Item> inventory = shop.getInventory();

        itemList.append("Total Shop Income: " + shop.getIncome()).append("\n");
        
        if (inventory.isEmpty()) {
            itemList.append("No items in the shop.").append("\n");
        } else {
            for (Item item : inventory) {
                itemList.append(item.getItemName()).append("\n");
            }
        }
        
        itemListArea.setText(itemList.toString());        
    }

    // Represents the action to be taken when the user wants to add clear the outputArea coontents
    private class ClearOutputAction extends AbstractAction {

        ClearOutputAction() {
            super("Clear Log");
        }
       
        // MODIFIES: this
        // EFFECTS: Adds an item to the shop 
        @Override
        public void actionPerformed(ActionEvent evt) {
            outputArea.setText("");
        }
    }

    // Represents the action to be taken when the user wants to add an Item to the Shop
    private class AddItemAction extends AbstractAction {

        AddItemAction() {
            super("Add Item");
        }

        // REQUIRES: itemName, type, quality, and description are non-empty Strings
        //           itemName is not one that already exists in the inventory
        //           price > 0         
        // MODIFIES: this
        // EFFECTS: Adds an item to the shop 
        @Override
        public void actionPerformed(ActionEvent evt) {
            String itemName = JOptionPane.showInputDialog("Enter item name:");
            String type = JOptionPane.showInputDialog("Enter item type:");
            String quality = JOptionPane.showInputDialog("Enter item quality:");
            int price = Integer.parseInt(JOptionPane.showInputDialog("Enter item price:"));
            String description = JOptionPane.showInputDialog("Enter item description:");
            Item newItem = new Item(itemName, type, quality, price, description);
            shop.addItem(newItem);
            outputArea.append("\n" + "Item '" + itemName + "' has been added to the shop!\n");
            addItemPopup();
            updateItemList();
        }

        // EFFECTS: Shows a messageDialog with an image confirming the item has been added
        private void addItemPopup() {
            ImageIcon soldIcon = new ImageIcon("./data/add.png");
            Image scaledIcon = soldIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledIcon);
    
            JLabel iconLabel = new JLabel(resizedIcon);
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
            JLabel messageLabel = new JLabel("Item added successfully!");
            messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(iconLabel);       
            panel.add(messageLabel);    

            JOptionPane.showMessageDialog(null, panel, "Item Added", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // Represents the action to be taken when the user wants to sell an Item from the Shop
    private class SellItemAction extends AbstractAction {

        SellItemAction() {
            super("Sell Item");
        }

        // EFFECTS: Prints out the list of items in the shop or states if the shop is empty
        @Override
        public void actionPerformed(ActionEvent evt) {
            String itemToView = JOptionPane.showInputDialog("What item do you want to sell?");
            ArrayList<Item> inventory = shop.getInventory();
            if (inventory.isEmpty()) {
                outputArea.setText("\n" +"The shop has nothing to sell!\n");
            } else {
                for (Item item : inventory) {
                    if (item.getItemName().equalsIgnoreCase(itemToView)) {
                        shop.removeItem(item);
                        outputArea.append("\n" +"Item has been sold!\n");
                        soldItemPopup();
                        updateItemList();
                        return;
                    }
                }
                outputArea.setText("\n" +"No item with that name in the shop!\n");
                updateItemList();
            }     
        }

        // EFFECTS: Shows a messageDialog with an image confirming the item has been sold
        private void soldItemPopup() {
            ImageIcon soldIcon = new ImageIcon("./data/sold.png");
            Image scaledIcon = soldIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledIcon);
    
            JLabel iconLabel = new JLabel(resizedIcon);
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
            JLabel messageLabel = new JLabel("Item sold successfully!");
            messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(iconLabel);       
            panel.add(messageLabel);    

            JOptionPane.showMessageDialog(null, panel, "Sale Confirmation", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // Represents the action to be taken when the user wants to view a list of items from the Shop
    private class ViewInventoryAction extends AbstractAction {

        ViewInventoryAction() {
            super("View Inventory");
        }

        // EFFECTS: Prints out the list of items in the shop or states if the shop is empty
        @Override
        public void actionPerformed(ActionEvent evt) {
            ArrayList<Item> inventory = shop.getInventory();
            StringBuilder inventoryList = new StringBuilder();

            if (inventory.isEmpty()) {
                outputArea.append("\n" + "The shop is currently empty!\n");
            } else {
                for (Item item : inventory) {
                    inventoryList.append("\n" +"Name: ").append(item.getItemName()).append("\n")
                    .append("Price: ").append(item.getPrice()).append("\n")
                    .append("--------------------------------\n"); 
                }
                outputArea.append(inventoryList.toString() + "\n");
            }
        }
    }

    // Represents the action to be taken when the user wants to view a single item from the Shop
    private class ViewItemAction extends AbstractAction {
        ViewItemAction() {
            super("View Item");
        }

        // EFFECTS: Prints out the qualities of a chosen item in the shop
        //          Otherwise States if the shop does not contain that item
        @Override
        public void actionPerformed(ActionEvent evt) {
            String itemToView = JOptionPane.showInputDialog("What item do you want to view?");
            ArrayList<Item> inventory = shop.getInventory();
            if (inventory.isEmpty()) {
                outputArea.append("The shop has nothing to view!\n");
            } else {
                for (Item item : inventory) {
                    if (item.getItemName().equalsIgnoreCase(itemToView)) {
                        outputArea.append("\n" + item.getItemDetails() + "\n");
                        return;
                    }
                }
                outputArea.append("\n" + "No item with that name in the shop!\n");
            }
        }
    }

    // Represents the action to be taken when the user wants to save the state of the Shop
    private class SaveShopAction extends AbstractAction {
        SaveShopAction() {
            super("Save Shop");
        }

        //EFFECTS: saves shop from file
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(shop);
                jsonWriter.close();
                outputArea.append("\n" + "Saved " + shop.getShopName() + " to " + JSON_STORE + "\n");
            } catch (FileNotFoundException e) {
                outputArea.append("\n" + "Unable to write to file: " + JSON_STORE + "\n");
            }        
        }
    }

    // Represents the action to be taken when the user wants to load a previous state of the Shop
    private class LoadShopAction extends AbstractAction {
        LoadShopAction() {
            super("Load Shop");
        }

        // MODIFIES: this
        // EFFECTS: loads shop from file
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                shop = jsonReader.read();
                outputArea.append("\n" + "Loaded " + shop.getShopName() + " from " + JSON_STORE + "\n");
                updateItemList();
            } catch (IOException e) {
                outputArea.append("\n" + "Unable to write to file: " + JSON_STORE + "\n");
            }        
        }
    }
}