package ui;

import model.Shop;

public class Main {
    public static void main(String[] args) throws Exception {
        // new ShopApp();
        new ShopAppGUI(new Shop("Test Shop"));

    }
}
