package org.example;

//product class
public class Product {
    private String Sku;
    private String name;
    private double price;
    private String department;
    private int quantity;

//constructor
    public Product(String SKU, String name, double price, String department) {
        this.Sku = Sku;
        this.name = name;
        this.price = price;
        this.department = department;
        this.quantity = 1;
    }

    //set & get
    //toString string format for 4 objects
    //to string with quantity - numbers now with the get cost input from user
    public String toString() {
        return String.format("%s (%s) -- %.2f -- %s", name, Sku, price, department);
    }

    public String toStringWithQuantity() {
        return String.format("%s\t%s (%s) -- %.2f -- %s", quantity, name, Sku, getCost(), department);
    }

    public void addQuantity() {
        quantity++;
    }

    public void removeQuantity() {
        quantity--;
    }

    public String getSKU() {
        return Sku;
    }

    public void setSKU(String SKU) {
        this.Sku = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return price * quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getQuantity() {
        return quantity;
    }
}