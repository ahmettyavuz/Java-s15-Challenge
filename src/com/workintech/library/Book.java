package com.workintech.library;

import java.time.LocalDate;

public class Book {
    private String bookID;
    private Author author;
    private String name;
    private Category category;
    private String edition;
    private LocalDate dateOfPurchase;
    private Status status;

    public Book(String bookID, Author author, String name, Category category, String edition, LocalDate dateOfPurchase, Status status) {
        this.bookID = bookID;
        this.author = author;
        this.name = name;
        this.category = category;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.status = status;
    }

    public String getBookID() {
        return bookID;
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getEdition() {
        return edition;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public Status getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", edition='" + edition + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                ", status=" + status +
                '}';
    }
}
