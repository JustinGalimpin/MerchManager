package ui;

import javax.swing.*;

import model.Item;
import model.Shop;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ShopAppGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Shop shop; // Your shop object
    private JTextArea outputArea; // Area to display output

    public ShopAppGUI(Shop shop) {
    }
    
}