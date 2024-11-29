import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {
    static class Book {
        private String title;
        private String author;
        private boolean isAvailable;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isAvailable = true;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void checkOut() {
            if (isAvailable) {
                isAvailable = false;
                System.out.println("Book checked out successfully.");
            } else {
                System.out.println("Book is already checked out.");
            }
        }

        public void returnBook() {
            if (!isAvailable) {
                isAvailable = true;
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book was not checked out.");
            }
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author + ", Status: " + (isAvailable ? "Available" : "Checked Out");
        }
    }

    static class LibraryCatalog {
        private ArrayList<Book> books;

        public LibraryCatalog() {
            books = new ArrayList<>();
        }

        public void addBook(String title, String author) {
            books.add(new Book(title, author));
            System.out.println("Book added successfully.");
        }

        public void searchByTitle(String title) {
            System.out.println("Searching for books with title: " + title);
            boolean found = false;
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    System.out.println(book);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No books found with the given title.");
            }
        }

        public void searchByAuthor(String author) {
            System.out.println("Searching for books by author: " + author);
            boolean found = false;
            for (Book book : books) {
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    System.out.println(book);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No books found by the given author.");
            }
        }

        public void checkOutBook(String title) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    book.checkOut();
                    return;
                }
            }
            System.out.println("Book not found in the catalog.");
        }

        public void returnBook(String title) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    book.returnBook();
                    return;
                }
            }
            System.out.println("Book not found in the catalog.");
        }

        public void displayBooks() {
            System.out.println("Library Catalog:");
            if (books.isEmpty()) {
                System.out.println("No books in the catalog.");
            } else {
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        }
    }

    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book by Title");
            System.out.println("3. Search Book by Author");
            System.out.println("4. Check Out Book");
            System.out.println("5. Return Book");
            System.out.println("6. Display All Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    catalog.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter book title to search: ");
                    title = scanner.nextLine();
                    catalog.searchByTitle(title);
                    break;
                case 3:
                    System.out.print("Enter author name to search: ");
                    author = scanner.nextLine();
                    catalog.searchByAuthor(author);
                    break;
                case 4:
                    System.out.print("Enter book title to check out: ");
                    title = scanner.nextLine();
                    catalog.checkOutBook(title);
                    break;
                case 5:
                    System.out.print("Enter book title to return: ");
                    title = scanner.nextLine();
                    catalog.returnBook(title);
                    break;
                case 6:
                    catalog.displayBooks();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
