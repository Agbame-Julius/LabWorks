package model;

import java.sql.Date;

public class Book {
    Integer bookID;
    String title;
    String author;
    String isbn;
    String genre;
    String availabililty;

    public Book(Integer bookID, String title, String author, String isbn, String genre, String availabililty) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.availabililty = availabililty;
    }


    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAvailability() {
        return availabililty;
    }

    public void setAvailability(String availabililty) {
        availabililty = availabililty;
    }
}
