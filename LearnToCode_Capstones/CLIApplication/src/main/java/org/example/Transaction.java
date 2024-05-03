package org.example;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;



//transaction class
//Comparable interface is used to compare an object of the same class with an instance of that class
public class Transaction implements Comparable<Transaction> {
    public static SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
    private String description;
    private double amount;
    private String vendor;
    private Date date;

//transaction class calling the conditions to set constructor
    public Transaction(Date date, String description, String vendor, double amount) {
        this.date = date;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //formatting the date
    public String toString() {
        String[] dateTimeSeparated = dateForm.format(date).split("\\|");
        return dateTimeSeparated[0] + " -- " +
                dateTimeSeparated[1] + " -- " +
                description + " -- " +
                vendor + " -- " +
                String.format("%.2f", amount);
    }


    //calender class to format the date
    public String toCal() {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        return "\n" + (dateForm.format(calendario.getTime()))
                + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount);
    }


    //getters and setters for the class objects

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    //this is overriding since we did a compare to class method at the top
    @Override
    public int compareTo(Transaction o) {
        return date.compareTo(o.date);
    }
}