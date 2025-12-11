/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurantordercalculator;

import java.util.Scanner;

public class RestaurantOrderCalculator {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Menu data (arrays)
        String[] itemNames = {"Burger", "Fries", "Soda", "Chicken Wrap", "Salad"};
        double[] itemPrices = {8.50, 3.00, 2.00, 7.25, 6.00};
        int[] quantities = new int[itemNames.length];  // all start at 0

        printWelcomeMessage();
        boolean ordering = true;

        while (ordering) {
            displayMenu(itemNames, itemPrices);

            int choiceIndex = getMenuChoice(in, itemNames.length);
            int qty = getQuantity(in, itemNames[choiceIndex]);

            // Add to quantities array
            quantities[choiceIndex] += qty;

            System.out.println("You added " + qty + " order(s) of " + itemNames[choiceIndex] + ".");
            System.out.println();

            // Ask if they want to order another item
            System.out.print("Would you like to order another item? (Y/N): ");
            String again = in.next();

            if (!again.equalsIgnoreCase("Y")) {
                ordering = false;
            }

            System.out.println();
        }

        // After ordering is done, calculate subtotal and show a simple summary
        System.out.println("--- Order Summary ---");
        System.out.printf("%-15s %5s %8s %10s%n", "Item", "Qty", "Price", "Line Total");

        for (int i = 0; i < itemNames.length; i++) {
            if (quantities[i] > 0) {
                double lineTotal = quantities[i] * itemPrices[i];
                System.out.printf("%-15s %5d %8.2f %10.2f%n",
                        itemNames[i], quantities[i], itemPrices[i], lineTotal);
            }
        }

        double subtotal = calculateSubtotal(quantities, itemPrices);
        System.out.printf("%nSubtotal: %.2f%n", subtotal);

        System.out.println();
        System.out.println("Thank you for using the Restaurant Order Calculator!");

        in.close();
    }

    // --- Methods ---

    public static void printWelcomeMessage() {
        System.out.println("Welcome to the Restaurant Order Calculator!");
        System.out.println("You can build an order from the menu, and I'll calculate the subtotal for you.");
        System.out.println();
    }

    public static void displayMenu(String[] names, double[] prices) {
        System.out.println("Menu:");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d) %-14s $%.2f%n", (i + 1), names[i], prices[i]);
        }
        System.out.println();
    }

    // Get a valid menu choice (returns index in array)
    public static int getMenuChoice(Scanner in, int maxChoice) {
        System.out.print("Enter the number of the item you want to order: ");
        int choice = in.nextInt();

        // Keep asking until valid
        while (choice < 1 || choice > maxChoice) {
            System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice + ".");
            System.out.print("Enter the number of the item you want to order: ");
            choice = in.nextInt();
        }

        // Convert 1–maxChoice to index 0–(maxChoice-1)
        return choice - 1;
    }

    // Get a positive quantity
    public static int getQuantity(Scanner in, String itemName) {
        System.out.print("Enter quantity for " + itemName + ": ");
        int qty = in.nextInt();

        while (qty < 1) {
            System.out.println("Quantity must be at least 1.");
            System.out.print("Enter quantity for " + itemName + ": ");
            qty = in.nextInt();
        }

        return qty;
    }

    // Calculate subtotal from quantities[] and prices[]
    public static double calculateSubtotal(int[] quantities, double[] prices) {
        double subtotal = 0.0;

        for (int i = 0; i < quantities.length; i++) {
            double lineTotal = quantities[i] * prices[i];
            subtotal += lineTotal;
        }

        return subtotal;
    }
}

