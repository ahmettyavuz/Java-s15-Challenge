package com.workintech.library;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User extends Person {
    private Set<Book> borrowedBooks = new HashSet<>();
    private static final int BOOK_LIMIT = 5;

    public User(String id, String name, String password) {
        super(id, name, password);
    }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() < BOOK_LIMIT && book.getStatus() == Status.AVAILABLE) {
            borrowedBooks.add(book);
            book.setStatus(Status.BORROWED);
            System.out.println(getName() + " isimli okur " + book.getName() + " isimli kitabı ödünç aldı.");

            System.out.println(getInvoice(book).toString());
        } else {
            System.out.println("Kitap ödünç alınmmış veya mevcut değil.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setStatus(Status.AVAILABLE);
            System.out.println(getName() + " isimli okur " + book.getName() + " isimli kitabı iade etti.");

        } else {
            System.out.println("Ödünç alınanlar listesinde kitap bulunamadı.");
        }
    }

    public Invoice getInvoice(Book book) {
        // For simplicity, let's assume a fixed amount per book
        double amount = 10.0;
        Invoice invoice = new Invoice(UUID.randomUUID().toString(), this, book, amount, new Date());
        return invoice;
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "borrowedBooks=" + borrowedBooks +
                '}';
    }
}