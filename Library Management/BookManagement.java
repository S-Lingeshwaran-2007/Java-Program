import java.util.*;

class Book {
    private int bookId;
    private String title;
    private String author;
    private double price;

    public Book(int bookId, String title, String author, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId +
                ", Title: " + title +
                ", Author: " + author +
                ", Price: " + price;
    }
}

public class BookManagement {
    static ArrayList<Book> bookList = new ArrayList<>();
    static HashMap<Integer, Book> bookMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== BOOK MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Add Book
    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        if (bookMap.containsKey(id)) {
            System.out.println("Book ID already exists!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        Book book = new Book(id, title, author, price);

        bookList.add(book);
        bookMap.put(id, book);

        System.out.println("Book Added Successfully.");
    }

    // View Books using Enhanced For Loop
    static void viewBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nBook Records:");
        for (Book b : bookList) {
            System.out.println(b);
        }
    }

    // Search using HashMap
    static void searchBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        Book b = bookMap.get(id);

        if (b != null)
            System.out.println(b);
        else
            System.out.println("Book not found.");
    }

    // Update Book
    static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        int id = sc.nextInt();

        Book b = bookMap.get(id);

        if (b == null) {
            System.out.println("Book not found.");
            return;
        }

        sc.nextLine();

        System.out.print("Enter New Title: ");
        b.setTitle(sc.nextLine());

        System.out.print("Enter New Author: ");
        b.setAuthor(sc.nextLine());

        System.out.print("Enter New Price: ");
        b.setPrice(sc.nextDouble());

        System.out.println("Book Updated Successfully.");
    }

    // Delete using Iterator
    static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();

        Iterator<Book> iterator = bookList.iterator();

        while (iterator.hasNext()) {
            Book b = iterator.next();

            if (b.getBookId() == id) {
                iterator.remove();
                bookMap.remove(id);
                System.out.println("Book Deleted Successfully.");
                return;
            }
        }

        System.out.println("Book not found.");
    }
}