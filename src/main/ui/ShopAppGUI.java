package ui;

import javax.swing.*;

import model.Item;
import model.Shop;

import java.awt.*;
import java.awt.event.ActionEvent;

// Code for the following class is heavily attributed to the AlarmSystem Demo example on edX
public class ShopAppGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Shop shop; // Your shop object
    private JTextArea outputArea; // Area to display output

    // Constructor representing the GUI of a Shop Class
    public ShopAppGUI(Shop shop) {
        this.shop = shop;

        setTitle("Shop Inventory Management");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addButtonPanel();
        addOutputArea();
        centreOnScreen();
        
        setVisible(true);
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
        add(buttonPanel, BorderLayout.WEST);
    }

    private void addOutputArea() {
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // Represents the action to be taken when the user wants to add an Item to the Shop
    private class AddItemAction extends AbstractAction {

        AddItemAction() {
            super("Add Item");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String itemName = JOptionPane.showInputDialog("Enter item name:");
            String type = JOptionPane.showInputDialog("Enter item type:");
            String quality = JOptionPane.showInputDialog("Enter item quality:");
            int price = Integer.parseInt(JOptionPane.showInputDialog("Enter item price:"));
            String description = JOptionPane.showInputDialog("Enter item description:");
            Item newItem = new Item(itemName, type, quality, price, description);
            if (shop.addItem(newItem)) {
                System.out.println("Item has been added to the shop!");
            } else {
                System.out.println("Error! The shop already has an item with that name.");
            }
        }
    }

    // Represents the action to be taken when the user wants to sell an Item from the Shop
    private class SellItemAction extends AbstractAction {

        SellItemAction() {
            super("Sell Item");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // Stub
        }
    }

    // Represents the action to be taken when the user wants to view a list of items from the Shop
    private class ViewInventoryAction extends AbstractAction {

        ViewInventoryAction() {
            super("View Inventory");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // Stub
        }
    }

    // Represents the action to be taken when the user wants to view a single item from the Shop
    private class ViewItemAction extends AbstractAction {

        ViewItemAction() {
            super("View Item");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // Stub
        }
    }

    // Represents the action to be taken when the user wants to save the state of the Shop
    private class SaveShopAction extends AbstractAction {

        SaveShopAction() {
            super("Save Shop");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // Stub
        }
    }

    // Represents the action to be taken when the user wants to load a previous state of the Shop
    private class LoadShopAction extends AbstractAction {

        LoadShopAction() {
            super("Load Shop");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // Stub
        }
    }
}