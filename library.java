import java.util.*;

// Book class to represent each book
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public void display() {
        System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", Status: " + (isIssued ? "Issued" : "Available"));
    }
}

// Library class to manage the collection of books
class Library {
    private List<Book> books = new ArrayList<>();
    private Queue<Integer> issuedBooks = new LinkedList<>();

    // Function to add a new book to the library
    public void addBook(int id, String title, String author) {
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    // Function to search for a book by its ID
    public void searchBookById(int id) {
        for (Book book : books) {
            if (book.id == id) {
                book.display();
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Function to search for a book by its title
    public void searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                book.display();
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Function to issue a book to a student
    public void issueBook(int id, int studentId) {
        for (Book book : books) {
            if (book.id == id && !book.isIssued) {
                book.isIssued = true;
                issuedBooks.add(studentId);
                System.out.println("Book issued successfully!");
                return;
            }
        }
        System.out.println("Book is either not available or already issued!");
    }

    // Function to return a book
    public void returnBook(int id) {
        for (Book book : books) {
            if (book.id == id && book.isIssued) {
                book.isIssued = false;
                issuedBooks.poll();
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("Book is not issued!");
    }

    // Function to list all books in the library
    public void listAllBooks() {
        books.sort(Comparator.comparingInt(b -> b.id));
        for (Book book : books) {
            book.display();
        }
    }

    // Function to delete a book from the library
    public void deleteBook(int id) {
        books.removeIf(book -> book.id == id);
        System.out.println("Book deleted successfully!");
    }
}

// Main class to demonstrate the library management system
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        int choice, id, studentId;
        String title, author;

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book by ID");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. List All Books");
            System.out.println("7. Delete Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    author = scanner.nextLine();
                    library.addBook(id, title, author);
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    id = scanner.nextInt();
                    library.searchBookById(id);
                    break;
                case 3:
                    System.out.print("Enter Book Title: ");
                    title = scanner.nextLine();
                    library.searchBookByTitle(title);
                    break;
                case 4:
                    System.out.print("Enter Book ID: ");
                    id = scanner.nextInt();
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextInt();
                    library.issueBook(id, studentId);
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    id = scanner.nextInt();
                    library.returnBook(id);
                    break;
                case 6:
                    library.listAllBooks();
                    break;
                case 7:
                    System.out.print("Enter Book ID: ");
                    id = scanner.nextInt();
                    library.deleteBook(id);
                    break;
                case 8:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
