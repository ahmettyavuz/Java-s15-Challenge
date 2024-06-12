package com.workintech.library;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Book> books = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return name;
    }
}