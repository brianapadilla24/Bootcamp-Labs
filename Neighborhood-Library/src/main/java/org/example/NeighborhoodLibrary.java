
package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//class
class Book {

    //Id, isbn, title
    //checcked in or out
    private int ID;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;

    public Book(int ID, String isbn, String title) {
        this.ID = ID;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = false;
        this.checkedOutTo = "";
    }

    // Getters and Setters
    public int getID() {
        return ID;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    // Method to check out a book
    public void checkOut(String name) {
        if (!isCheckedOut) {
            isCheckedOut = true;
            checkedOutTo = name;
            System.out.println(title + " has been checked out to " + name);
        } else {
            System.out.println(title + " is already checked out.");
        }
    }

    // Method to check in a book
    public void checkIn() {
        if (isCheckedOut) {
            isCheckedOut = false;
            checkedOutTo = "";
            System.out.println(title + " has been checked in.");
        } else {
            System.out.println(title + " is already checked in.");
        }
    }
}

//neighborhood library class

public class NeighborhoodLibrary {
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        initializeLibrary();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Library!");
            System.out.println("1. Show available books");
            System.out.println("2. Show checked out books");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAvailableBooks();
                    break;
                case 2:
                    showCheckedOutBooks();
                    break;
                case 3:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void initializeLibrary() {
        // Initialize the library with some books
        books.add(new Book(1, "978-0061120084", "To Kill a Mockingbird"));
        books.add(new Book(2, "978-1400034772", "1984"));
        books.add(new Book(3, "978-1451673319", "The Great Gatsby"));
        // Add more books as needed
    }

    private static void showAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isCheckedOut()) {
                System.out.println("ID: " + book.getID() + ", ISBN: " + book.getIsbn() + ", Title: " + book.getTitle());
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the book you want to checkout, or enter 0 to go back to the home screen:");
        int bookID = scanner.nextInt();
        if (bookID != 0) {
            System.out.print("Enter your name: ");
            scanner.nextLine(); // consume newline
            String name = scanner.nextLine();
            checkoutBook(bookID, name);
        }
    }

    private static void showCheckedOutBooks() {
        System.out.println("Checked Out Books:");
        for (Book book : books) {
            if (book.isCheckedOut()) {
                System.out.println("ID: " + book.getID() + ", ISBN: " + book.getIsbn() + ", Title: " + book.getTitle() + ", Checked Out To: " + book.getCheckedOutTo());
            }
        }

        System.out.println("C - Check in a book");
        System.out.println("X - Go back to the home screen");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().toUpperCase();
        if (choice.equals("C")) {
            System.out.print("Enter the ID of the book you want to check in: ");
            int bookID = scanner.nextInt();
            checkInBook(bookID);
        }
    }

    private static void checkoutBook(int bookID, String name) {
        for (Book book : books) {
            if (book.getID() == bookID && !book.isCheckedOut()) {
                book.checkOut(name);
                return;
            }
        }
        System.out.println("Invalid book ID or the book is already checked out.");
    }

    private static void checkInBook(int bookID) {
        for (Book book : books) {
            if (book.getID() == bookID && book.isCheckedOut()) {
                book.checkIn();
                return;
            }
        }
        System.out.println("Invalid book ID or the book is already checked in.");
    }
}





