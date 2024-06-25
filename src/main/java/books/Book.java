package books;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Book {

    //==================================== ATRIBUT ====================================
    public String bookId;
    public String title;
    public String author;
    public String category;
    public String borrower;
    public int stock;
    public int duration;


    //ArrayList untuk menyimpan list buku yang terdaftar.
    public static ObservableList<Book> arr_bookList = FXCollections.observableArrayList();

    //ArrayList untuk menyimpan list buku yang sedang dipinjam mahasiswa.
    public static ObservableList<Book> arr_borrowedBook = FXCollections.observableArrayList();


    //====================================== METHOD ======================================

    // 4 Method konstruktor dengan nama yang sama, bertujuan untuk menerapkan fungsi overloading (Modul 3)
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


    //=================================== SETTER METHOD ====================================
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    //=================================== GETTER METHOD ==================================
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

    public int getStock() {
        return stock;
    }

    public int getDuration() {
        return duration;
    }

    public String getBorrower() {
        return borrower;
    }

}