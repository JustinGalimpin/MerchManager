package ui;

import javax.swing.*;

import model.Item;
import model.Shop;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

// Code for the following class is heavily attributed to the AlarmSystem Demo example on edX
public class ShopAppGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Shop shop; // Your shop object
    private JTextArea outputArea; // Area to display output

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

        // Add buttons with ActionEvents
		buttonPanel.add(new JButton(new AddItemAction()));
        // buttonPanel.add(createButton("Sell Item", new RemoveItemAction()));
        // buttonPanel.add(createButton("Save Shop", new SaveShopAction()));
        // buttonPanel.add(createButton("Load Shop", new LoadShopAction()));
        // buttonPanel.add(createButton("View Income", new ViewIncoomeAction()));
        // buttonPanel.add(createButton("Display Items", new DisplayItemsAction()));

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

    
}