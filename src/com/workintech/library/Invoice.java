package com.workintech.library;

import java.util.Date;

public class Invoice {
    private final String invoiceID;
    private final User user;
    private final Book book;
    private final double amount;
    private final Date dateIssued;

    public Invoice(String invoiceID, User user, Book book, double amount, Date dateIssued) {
        this.invoiceID = invoiceID;
        this.user = user;
        this.book = book;
        this.amount = amount;
        this.dateIssued = dateIssued;
    }

    @Override
    public String toString() {
        return "Fatura\n" +
                "Fatura numarası=" + invoiceID + '\n' +
                "Okur=" + user.getName() + '\n' +
                "Kitap=" + book.getName() + '\n' +
                "Fiyat=" + amount + '\n' +
                "Kitap ödünç alma zamanı=" + dateIssued +
                '}';
    }
}
