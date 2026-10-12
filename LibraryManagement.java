import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Book {
int bookId;
String title;
String author;
boolean issued;
String memberName;
LocalDate issueDate;


Book(int bookId, String title, String author) {
    this.bookId = bookId;
    this.title = title;
    this.author = author;
    this.issued = false;
    this.memberName = "";
    this.issueDate = null;
}

void displayBook() {
    System.out.println("Book ID: " + bookId);
    System.out.println("Title: " + title);
    System.out.println("Author: " + author);
    System.out.println("Availability: " + (issued ? "Issued" : "Available"));
    System.out.println("-----------------------------");
}


}

public class LibraryManagement {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);


    Book[] books = new Book[100];
    int count = 0;
    int choice;

    do {
        System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Book");
        System.out.println("2. Display Books");
        System.out.println("3. Search Book");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Check Book Availability");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");

        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                if (count == books.length) {
                    System.out.println("Library inventory is full.");
                    break;
                }

                System.out.print("Enter Book ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                boolean exists = false;

                for (int i = 0; i < count; i++) {
                    if (books[i].bookId == id) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Book ID already exists.");
                    break;
                }

                System.out.print("Enter Book Title: ");
                String title = sc.nextLine();

                System.out.print("Enter Author Name: ");
                String author = sc.nextLine();

                books[count] = new Book(id, title, author);
                count++;

                System.out.println("Book added successfully!");
                break;

            case 2:
                if (count == 0) {
                    System.out.println("No books available in the library.");
                } else {
                    for (int i = 0; i < count; i++) {
                        books[i].displayBook();
                    }
                }
                break;

            case 3:
                System.out.print("Enter Book ID to Search: ");
                int searchId = sc.nextInt();

                boolean found = false;

                for (int i = 0; i < count; i++) {
                    if (books[i].bookId == searchId) {
                        books[i].displayBook();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Book not found.");
                }
                break;

            case 4:
                System.out.print("Enter Book ID to Issue: ");
                int issueId = sc.nextInt();
                sc.nextLine();

                boolean issueFound = false;

                for (int i = 0; i < count; i++) {
                    if (books[i].bookId == issueId) {
                        issueFound = true;

                        if (books[i].issued) {
                            System.out.println("Book is already issued.");
                        } else {
                            System.out.print("Enter Member Name: ");
                            books[i].memberName = sc.nextLine();

                            books[i].issueDate = LocalDate.now();
                            books[i].issued = true;

                            System.out.println("Book issued successfully!");
                            System.out.println("Issue Date: " + books[i].issueDate);
                        }
                        break;
                    }
                }

                if (!issueFound) {
                    System.out.println("Book not found.");
                }
                break;

            case 5:
                System.out.print("Enter Book ID to Return: ");
                int returnId = sc.nextInt();

                boolean returnFound = false;

                for (int i = 0; i < count; i++) {
                    if (books[i].bookId == returnId) {
                        returnFound = true;

                        if (!books[i].issued) {
                            System.out.println("This book has not been issued.");
                        } else {
                            LocalDate returnDate = LocalDate.now();

                            long days = ChronoUnit.DAYS.between(
                                books[i].issueDate, returnDate
                            );

                            long overdueDays = Math.max(0, days - 14);
                            long fine = overdueDays * 5;

                            System.out.println("\n===== BOOK RETURN DETAILS =====");
                            System.out.println("Book Title: " + books[i].title);
                            System.out.println("Member Name: " + books[i].memberName);
                            System.out.println("Issue Date: " + books[i].issueDate);
                            System.out.println("Return Date: " + returnDate);
                            System.out.println("Days Kept: " + days);
                            System.out.println("Overdue Fine: Rs." + fine);

                            books[i].issued = false;
                            books[i].memberName = "";
                            books[i].issueDate = null;

                            System.out.println("Book returned successfully!");
                        }
                        break;
                    }
                }

                if (!returnFound) {
                    System.out.println("Book not found.");
                }
                break;

            case 6:
                System.out.print("Enter Book ID to Check Availability: ");
                int checkId = sc.nextInt();

                boolean checkFound = false;

                for (int i = 0; i < count; i++) {
                    if (books[i].bookId == checkId) {
                        checkFound = true;

                        if (books[i].issued) {
                            System.out.println("Book is currently issued.");
                        } else {
                            System.out.println("Book is available.");
                        }
                        break;
                    }
                }

                if (!checkFound) {
                    System.out.println("Book not found.");
                }
                break;

            case 7:
                System.out.println("Thank you for using the Library Management System!");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }

    } while (choice != 7);

    sc.close();
}


}
