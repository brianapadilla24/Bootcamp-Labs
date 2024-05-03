package org.example;


//HomeScreen() {
//**Continues to run until the user chooses to exit
//D) Add deposit - prompt user for the deposit informaiton & save it to the csv file
//P) Make Payment - prompt user for the debit information & save it to the csv file
//L)Ledger - display the ledger screen
//X) Exit the application
//}

//Ledger() {
//A) All - display all entries
//D) Deposits - Display only the entries that are deposits into the account
//P) Payments Display only the negative entries (payments)
//R)Reports - New screen that allows the user to run pre-defined reports
// or to run a custom search
// - 1) Month To Date
// - 2) Previous Month
// - 3) Year To Date
// - 4) Previous Year
// - 5) Search By Vendor - prompt the user for the vendor name
//***6) Custom Search -
//     -Start Date
//     -End Date
//     -Description
//     -Vendor
//     -Amount
//*** if the user enters a value for a field you should filter
//***if the user does not enter a value you should Not filter
// - 0) Back - go back
// H) Home go back to home page

import java.util.InputMismatchException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;


//screen class
public class Screen {

    //scanner
    private static Scanner input = new Scanner(System.in);
    //transaction
    private TransactionHandler transaction = new TransactionHandler();

    //homescreen method
    public void homeScreen() {
        //true
        while (true) {
            System.out.println("""
                    ✧･ﾟ: *✧･ﾟ:* ༊*·˚
                    Welcome to Count Cloud!
                     
                     *+:｡.｡　Please select:　｡.｡:+*
                    →  D) Add deposit
                    →  P) Make payment
                    →  L) Show ledger
                    →  X) Exit
 
                    ✧･ﾟ: *✧･ﾟ:* ༊*·˚
                    """);

            //to Upper
            switch (input.nextLine().toUpperCase()) {
                case "D" -> addDeposit();
                case "P" -> addPayment();
                case "L" -> showLedger();
                case "X" -> {
                    return;
                }
            }
        }
    }

    //add deposit
    public void addDeposit() {
        System.out.println("" +
                "*+:｡.｡　Please enter comment for deposit:　｡.｡:+*");
        String description = input.nextLine();
        System.out.println("" +
                "*+:｡.｡ Please enter name receiving deposit by:　｡.｡:+*");
        String vendor = input.nextLine();
        System.out.println("*+:｡.｡ Please enter amount deposit:　｡.｡:+*");
        double amount = input.nextDouble();
        input.nextLine();
        if (amount <= 0) {
            System.out.println("⋘ ᴛʀʏ ʟᴀᴛᴇʀ... ⋙");
            return;
        }
        transaction.writeTransaction(new Transaction(
                new Date(),
                description,
                vendor,
                amount
        ));
    }

    public void addPayment() {
        System.out.println("✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧" +
                "Please enter a description for payment:" +
                "✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧");
        String description = input.nextLine();
        System.out.println("✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧" +
                "Deposit to: " +
                "✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧");
        String vendor = input.nextLine();
        System.out.println("✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧" +
                "Please enter the amount paid:" +
                "✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧");
        double amount = input.nextDouble();
        input.nextLine();
        if (amount <= 0) {
            System.out.println("⋘ ᴛʀʏ ʟᴀᴛᴇʀ... ⋙");
            return;
        }
        transaction.writeTransaction(new Transaction(
                new Date(),
                description,
                vendor,
                amount * -1
        ));
    }

    public void showLedger() {
        while (true) {
            System.out.println("""
                    ✧･ﾟ: *✧･ﾟ:* ༊*·˚
                    How would you like to view the ledger?
                    →  A) All
                    →  D) Deposits
                    →  P) Payments
                    →  R) Reports
                    →  H) Home
                    ✧･ﾟ: *✧･ﾟ:* ༊*·˚
                    """);

            switch (input.nextLine().toUpperCase()) {
                case "A" -> displayList(transaction.getTransactionList());
                case "D" -> displayList(transaction.filter(false));
                case "P" -> displayList(transaction.filter(true));
                case "R" -> reports();
                case "H" -> {
                    return;
                }
                default -> System.out.println("⋘ ᴛʀʏ ʟᴀᴛᴇʀ... ⋙");
            }
        }
    }

    //report: option filter and search
    public void reports() {
        while (true) {
            System.out.println("""
                    ✧･ﾟ: *✧･ﾟ:* ༊*·˚
                    Filters/ Search by vendor:
                    →  1) Month to Date
                    →  2) Previous Month
                    →  3) Year to Date
                    →  4) Previous Year
                    →  5) Search by Vendor
                    →  6) Custom Search
                    →  0) Back
                    ✧･ﾟ: *✧･ﾟ:* ༊*·˚
                    """);
            try {
                switch (input.nextInt()) {
                    case 1 -> displayList(transaction.monthToDate());
                    case 2 -> displayList(transaction.previousMonth());
                    case 3 -> displayList(transaction.yearToDate());
                    case 4 -> displayList(transaction.previousYear());
                    case 5 -> {
                        input.nextLine();
                        searchByVendor();
                    }
                    case 6 -> {
                        input.nextLine();
                        customSearch();
                    }
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("⋘ ᴛʀʏ ʟᴀᴛᴇʀ... ⋙");
                }
            } catch (InputMismatchException e) {
                System.out.println("⋘ ᴛʀʏ ʟᴀᴛᴇʀ... ⋙");
            }
        }
    }
    public void customSearch() {
        TransactionFilter tf = new TransactionFilter();
        var sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("" +
                "✦✧✦✧ " +
                "Date. Description. Vendor. Min/Max amount. " +
                "✦✧✦✧");
        try {
            System.out.println(": ̗̀➛" +
                    "Start date (yyyy-mm-dd):");
            String in = input.nextLine();
            if (!in.isEmpty()) tf.setStartDate(sdf.parse(in));

            System.out.println("" +
                    ": ̗̀➛" +
                    "End date (yyyy-mm-dd):");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setEndDate(sdf.parse(in));

            System.out.println("" +
                    ": ̗̀➛" +
                    "Description:");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setDescription(in);

            System.out.println("" +
                    ": ̗̀➛" +
                    "Vendor:");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setVendor(in);

            System.out.println(":" +
                    " ̗̀➛" +
                    "Minimum amount:");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setAmountMin(Double.parseDouble(in));

            System.out.println("" +
                    "" +
                            " ̗̀➛" +
                    "Maximum amount:");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setAmountMax(Double.parseDouble(in));
        } catch (ParseException | InputMismatchException e) {
            System.out.println("⋘ ᴛʀʏ ʟᴀᴛᴇʀ... ⋙");
            return;
        }
        displayList(transaction.filter(tf));
    }

    //vendor name
    public void searchByVendor() {
        System.out.println("✬ Vendor name: ✬");
        String vendor = input.nextLine();
        displayList(transaction.filter(vendor));
    }

    //display list of transaction
    public void displayList(List<Transaction> transactions) {
        for (Transaction transaction : transactions)
            System.out.println(transaction);
    }
}



