package books;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Book {

    // Attributes
    private String bookId;
    private String title;
    private String author;
    private String category;
    private String borrower;
    private int stock;
    private int duration;

    // ObservableLists to store registered and borrowed books
    public static ObservableList<Book> arr_bookList = FXCollections.observableArrayList();
    public static ObservableList<Book> arr_borrowedBook = FXCollections.observableArrayList();

    // Constructors
    public Book() {
    }

    public Book(String category) {
        this.category = category;
    }

    public Book(String bookId, int stock, int duration, String borrower) {
        this.bookId = bookId;
        this.stock = stock;
        this.duration = duration;
        this.borrower = borrower;
    }

    public Book(String bookId, String title, String author, String category, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    // Setter Methods
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Getter Methods
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getBorrower() {
        return borrower;
    }

    public int getStock() {
        return stock;
    }

    public int getDuration() {
        return duration;
    }
    public static void filterAndDisplayBorrowedBooks(TableView<Book> tableView, String borrowerId) {
        tableView.getItems().clear();
        System.out.println("Borrowed books list size: " + arr_borrowedBook.size());
        for (Book borrowedBook : arr_borrowedBook) {
            System.out.println("Checking book: " + borrowedBook.getTitle() + " borrowed by: " + borrowedBook.getBorrower());
            if (borrowedBook.getBorrower() != null && borrowedBook.getBorrower().equals(borrowerId)) {
                tableView.getItems().add(borrowedBook);
                System.out.println("Added book: " + borrowedBook.getTitle());
            }
        }
    }
}
