# CPSC 210 Project - Phase 0

## The Proposal
My project will be an **"Shop Inventory/Management"** application. The application allows a user to be in charge of and manage the inventory of their own shop. Possible features include the following:
- Adding items to the shop to be purchased
- Categorizing/defining specific qualities for each added item 
    - Possible categories include what type of item is it (e.g. is it a type of clothing, a type of consumable, etc.), the rarity of the item, the price of the item, and a general description of the item
- Filtering for a list of specific types of items available in the shop
- Selling items in the shop
    - Tracking the total money earned from selling items
- Applying a sale or discount to an item(s) in the shop
- Creating, saving, and/or loading a new shop (stretch goal/feature)

The application can be used personally for an individual to keep track of and manage items they may want to sell to others. Alternatively, the application could be used for more creative/game-like purposes, such as for individuals who want a way to manage a fictional shop in a role-playing game they may be running with friends. The shop itself works as a "base" for any type of theme the user would want; the user could use the application to create a jewelry shop (where the items the shop sells are jewelry), a restaurant (the items being types of food/dishes), or even a futuristic / medieval-era inspired shop.

This project is of interest to me because I find the application has the potential to test my understanding of several core elements of coding (creating and manipulating objects, manipulating Arrays, etc.) in a way that will not only improve my understanding of coding but ultimately leave me with an application that my friends and I could utilize for our own recreational use.

## User Stories for Phase 0

- As a user, I want to be able to **create a new item to add to the shop** and **specify the item's name, type, rarity, cost, and description.**
- As a user, I want to be able to **view a list of all the available items** in the shop and **search the list for a specific item by name**.
- As a user, I want to be able to **select an item from the shop and modify one of its previous assigned qualities**
- As a user, I want to be able to **sell (remove) an item from the shop**
- As a user, I want to be able to **track the total income of the shop** based off of items that are sold (removed)

## User Stories for Phase 2
- As a user, I want to be able to **have the option to save the current state of my shop**
- As a user, I want to be able to **have the option to reload a previously saved shop state** and continue from where I left off

## Instructions for End User for Phase 3

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by **clicking the button labelled "Add Item" and entering the required inputs**.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by **clicking the button labelled "View Inventory"**. Alternatively, you can click the **"View Item"** button if you wish to search up the details of a specific Item or the **"Remove Item"** button if you wish to remove an Item from the list.
- You can locate my visual component by **adding a new item with "Add Item"** or **selling an item with "Sell Item"**
- You can save the state of my application by **clicking the button labelled "Save Shop".**
- You can reload the state of my application by **clicking the button labelled "Load Shop".**

## Phase 4: Task 2
Below is a representative sample of events that can occur when the program runs:
- Item added to inventory: Armor
- Item added to inventory: Shield
- Item added to inventory: Pizza
- Pizza price updated to: 100
- Item sold from inventory: Pizza
- Shop income increased by: 100

## Phase 4: Task 3

Upon reflecting on the design of my project's UML Diagram, one thing that stood out to me is that there is currently a **uni-directional reelationship** between `Shop` and `Item`. While a Shop contains a collection of items, it makes sense that a singular instance of an Item can only belong to one Shop. As such, I would refactor this relationship to become **bi-directional**. This would also help in future-proofing my program, such as if I wanted to be able to have multiple Shops with items rather than just one; this change would make it so an Item could not accidentally be added to multiple Shops.

Another refactor would be to introduce the **Observer pattern** between `ShopAppGUI`/`ShopApp` and `Shop`/`Item`. In using this pattern, Shop would be the Subject (as most of the changes in the state of Items occur in the Shop), and the ShopAppGUI/ShopApp would be Observers. This would make the system more flexible, especially if additional UI components or even different views of the Shop were introduced. For instance, changes in item quantity or price could automatically trigger updates in the GUI, reducing the need for the GUI to continuously check for changes. 