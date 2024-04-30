package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//screens
public class Screen {
    //scanner
    private static Scanner scanner = new Scanner(System.in);
    //list array
    private static List<Product> products = new ArrayList<>();
    //cart
    private List<Product> cart;

    //cart with array
    public Screen() {
        cart = new ArrayList<>();
    }

    //display my homescreen
    public void homeScreen() {
        while (true) {
            System.out.println("""
                     ‧₊˚❀༉‧₊˚. Welcome to Targift! ‧₊˚❀༉‧₊˚.
                      
                      What would you like to do? : ‧₊˚❀༉‧₊˚.
                    1. Display your products
                    2. Display your cart
                    3. Exit
                    
                    ‧₊˚❀༉‧₊˚.
                    """);
            switch (scanner.nextInt()) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    displayCart();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("‧₊˚❀༉‧₊˚. Please try again. ‧₊˚❀༉‧₊˚.");
            }
        }
    }

    //display cart
    private void displayCart() {
        while (true) {
            for (Product producto : cart) {
                System.out.println(producto.toStringWithQuantity());
            }
            System.out.println(""" 
                       ‧₊˚❀༉‧₊˚.
                       Please select :
                       1. Check out ・❥・
                       2. Remove item from your cart ・❥・
                       3. Go back to homepage  ・❥・
                       ‧₊˚❀༉‧₊˚.
                    """);
            switch (scanner.nextInt()) {
                case 1:
                    checkOut();
                    return;
                case 2:
                    removeItemFromCart();
                    break;
                case 3:
                    return;
            }
        }
    }

    //display checkout

    private void checkOut() {
        System.out.println("‧₊˚❀༉‧₊˚." +
                "Please give your amount to cash register." +
                "‧₊˚❀༉‧₊˚.");
        double payment = scanner.nextDouble();
        double totalCost = 0.0;
        for (Product producto : cart)
            totalCost += producto.getCost();
        if (payment < totalCost) {
            System.out.println("・❥・ Insufficient funds! ・❥・");
            return;
        }
        Receipt recibo = new Receipt(cart, totalCost, payment);
        System.out.println(recibo);
        saveReceipt(recibo);
        cart = new ArrayList<>();
        System.out.println(" ‧₊˚❀༉‧₊˚." +
                "Thank you for shopping at Targift!" +
                " ‧₊˚❀༉‧₊˚.");
    }

    //display the products
    private void displayProducts() {
        for (Product producto : products)
            System.out.println(producto);
        while (true) {
            System.out.println("""
                    ⋆｡‧˚ʚ ❀ ɞ˚‧｡⋆
                    ..
                    Please select:
                    1. Filter/Search Products
                    2. Add + product to your cart
                    3. Go back to home.
                    ..
                    ⋆｡‧˚ʚ ❀ ɞ˚‧｡⋆
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    searchProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("・❥・ error ・❥・");
            }
        }
    }

    //search products using filter
    private void searchProducts() {
        System.out.println("""
                ‧₊˚❀༉‧₊˚.
                Use the following for search/filter products:
                𝒔𝒌𝒖 <sku> 
                𝒏𝒂𝒎𝒆 <name> ˖⁺‧₊˚❀˚₊‧⁺˖
                𝐩𝐫𝐢𝐜𝐞 above <amount>
                𝒑𝒓𝒊𝒄𝒆 below <amount>
                𝒅𝒆𝒑𝒂𝒓𝒕𝒎𝒆𝒏𝒕 <department>
                ‧₊˚❀༉‧₊˚.
                """);
                 //sku
        String[] command = scanner.nextLine().split(" ");
        switch (command[0].toLowerCase()) {
            case "sku":
                for (Product producto : products) {
                    if (producto.getSKU().contains(command[1].toUpperCase()))
                        System.out.println(producto);
                }
                break;
                //name
            case "name":
                for (Product producto : products) {
                    if (producto.getName().contains(command[1]))
                        System.out.println(producto);
                }
                break;
                //price
            case "price":
                if (command[1].equals("above")) {
                    for (Product producto : products) {
                        if (producto.getPrice() > Double.parseDouble(command[2]))
                            System.out.println(producto);
                    }
                } else if (command[1].equals("below")) {
                    for (Product producto : products) {
                        if (producto.getPrice() < Double.parseDouble(command[2]))
                            System.out.println(producto);
                    }
                } else System.out.println(".☘︎ ݁˖" +
                        "Please try again. " +
                        ".☘︎ ݁˖");
                break;
                //department
            case "department":
                for (Product producto : products) {
                    if (producto.getDepartment().contains(command[1]))
                        System.out.println(producto);
                }
                break;
                //default
            default:
                System.out.println(".☘︎ ݁˖ invalid ..  please try again .☘︎ ݁˖");
        }
    }

    //adding my product to the cart
    private void addProductToCart() {
        System.out.println("" +
                " ❀。• *₊°。 ❀°。" +
                "Please enter 𝒔𝒌𝒖 of product " +
                "❀。• *₊°。 ❀°。");
        //scanner
        //for loop equals ignore case of sku input
        String sku = scanner.nextLine();
        for (Product producto : products) {
            if (producto.getSKU().equalsIgnoreCase(sku)) {
                for (Product prod : cart) {
                    if (prod.getSKU().equalsIgnoreCase(sku)) {
                        prod.addQuantity();
                        return;
                    }
                }
                //add
                cart.add(producto);
                return;
            }
        }
        //else
        System.out.println("" +
                "⋆｡‧˚ʚ ❀ ɞ˚‧｡⋆" +
                "Cannot allocate Product." +
                "⋆｡‧˚ʚ ❀ ɞ˚‧｡⋆");
    }

    //removing the item from the cart
    private void removeItemFromCart() {
        System.out.println("" +
                ".☘︎ ݁˖" +
                "What is SKU of the product you want to remove?" +
                ".☘︎ ݁˖");
        //scanner reading sku
        //for loop of the product making sure its the sku, and removing it
        String sku = scanner.nextLine();
        for (Product producto : cart) {
            if (producto.getSKU().equals(sku)) {
                if (producto.getQuantity() > 1) {
                    producto.removeQuantity();
                    return;
                }
                //remove
                cart.remove(producto);
                return;
            }
        }
        //else
        System.out.println("" +
                ".☘︎ ݁˖" +
                "Sorry love. " +
                "Product not found." +
                ".☘︎ ݁˖");
    }

    //my receipt

    private void saveReceipt(Receipt recibo) {
        Date date = recibo.getDate();
        String filename =
                date.getYear() + "" + date.getMonth() + "" + date.getDay() + "" + date.getHours() + "" + date.getMinutes();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/" + filename + ".txt"))) {
            bw.write(recibo.toString());
        } catch (IOException e) {
            System.out.println("・❥・ Error ・❥・");
        }
    }

    //loading the products
    public static void loadProducts() {
        //buff reader
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/products.csv"))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                products.add(new Product(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
            }
        } catch (IOException e) {
            System.out.println("・❥・ error ・❥・");
        }
    }
}