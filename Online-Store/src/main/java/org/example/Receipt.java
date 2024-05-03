<<<<<<< HEAD
package org.example;

import java.util.Date;
import java.util.List;


//This class is the receipt
//Date, Product, Total, Amount Paid, Change Given
public class Receipt {
    private Date date;

    //List array
    private List<Product> items;
    private double total;
    private double amountPaid;
    private double changeGiven;

    //call array with conditions
    public Receipt(Date date, List<Product> items, double total, double amountPaid) {
        this.date = date;
        this.items = items;
        this.total = total;
        this.amountPaid = amountPaid;
        //change
        if (amountPaid > total)
            changeGiven = amountPaid - total;
        else changeGiven = 0;
    }

    //receipt with list array called with conditions
    public Receipt(List<Product> items, double total, double amountPaid) {
        this.date = new Date();
        this.items = items;
        this.total = total;
        this.amountPaid = amountPaid;
        if (amountPaid > total)
            changeGiven = amountPaid - total;
        else changeGiven = 0;
    }

    //Date
    public String toString() {
        String stringg = date.toString();
        for (Product p : items) {
            stringg += "\n" + p.toStringWithQuantity();
        }
        stringg += "\nTotal: " + total;
        stringg += "\nAmount paid: " + amountPaid;
        stringg += "\nChange given: " + changeGiven;
        return stringg;
    }

    public Date getDate() {
        return date;
    }

    //items from the list array
    public List<Product> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChangeGiven() {
        return changeGiven;
    }
=======
package org.example;

import java.util.Date;
import java.util.List;


//This class is the receipt
//Date, Product, Total, Amount Paid, Change Given
public class Receipt {
    private Date date;

    //List array
    private List<Product> items;
    private double total;
    private double amountPaid;
    private double changeGiven;

    //call array with conditions
    public Receipt(Date date, List<Product> items, double total, double amountPaid) {
        this.date = date;
        this.items = items;
        this.total = total;
        this.amountPaid = amountPaid;
        //change
        if (amountPaid > total)
            changeGiven = amountPaid - total;
        else changeGiven = 0;
    }

    //receipt with list array called with conditions
    public Receipt(List<Product> items, double total, double amountPaid) {
        this.date = new Date();
        this.items = items;
        this.total = total;
        this.amountPaid = amountPaid;
        if (amountPaid > total)
            changeGiven = amountPaid - total;
        else changeGiven = 0;
    }

    //Date
    public String toString() {
        String stringg = date.toString();
        for (Product p : items) {
            stringg += "\n" + p.toStringWithQuantity();
        }
        stringg += "\nTotal: " + total;
        stringg += "\nAmount paid: " + amountPaid;
        stringg += "\nChange given: " + changeGiven;
        return stringg;
    }

    public Date getDate() {
        return date;
    }

    //items from the list array
    public List<Product> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChangeGiven() {
        return changeGiven;
    }
>>>>>>> f4e8a83aca088e80a20dc52215ad53a5b4bfc449
}