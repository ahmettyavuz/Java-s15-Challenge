package com.workintech.library;

import java.time.LocalDate;
import java.util.*;

public class Library {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public Library() {
        books = new HashMap<>();
        users = new HashMap<>();

        books.put("1", new Book("1", new Author("Sabahattin Ali"), "Kürk Mantolu Madonna", new Category("Edebiyat"), "false", LocalDate.of(2024, 1, 12), Status.AVAILABLE));
        books.put("2", new Book("2", new Author("Yaşar Kemal"), "İnce Memed", new Category("Edebiyat"), "false", LocalDate.of(2024, 1, 12), Status.AVAILABLE));
        books.put("3", new Book("3", new Author("Orhan Pamuk"), "Masumiyet Müzesi", new Category("Aşk"), "false", LocalDate.of(2024, 1, 12), Status.AVAILABLE));
        books.put("4", new Book("4", new Author("Orhan Pamuk"), "Benim Adım Kırmızı", new Category("Aşk"), "false", LocalDate.of(2024, 1, 12), Status.AVAILABLE));
        books.put("5", new Book("5", new Author("Yaşar Kemal"), "Orta Direk", new Category("Roman"), "false", LocalDate.of(2024, 1, 12), Status.AVAILABLE));
        books.put("6", new Book("6", new Author("Sabahattin Ali"), "Kuyucaklı Yusuf", new Category("Roman"), "false", LocalDate.of(2024, 1, 12), Status.AVAILABLE));

        users.put("1", new User("1", "ahmet", "123"));
        users.put("2", new User("2", "fevzi", "123"));
        users.put("3", new User("3", "mehmet", "123"));
    }


    public void addBook(Book book) {
        books.put(book.getBookID(), book);
        book.getAuthor().addBook(book);
        book.getCategory().addBook(book);
        System.out.println("Kitap Eklendi: " + book.getName());
    }

    public void removeBook(String bookID) {
        Book book = books.remove(bookID);
        if (book != null) {
            System.out.println("Kitap Çıkarıldı: " + book.getName());
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public void updateBook(Book book) {
        books.put(book.getBookID(), book);
        System.out.println("Kitap güncellendi: " + book.getName());
    }

    public Book getBook(String bookID) {
        return books.get(bookID);
    }

    public List<Book> getBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByCategory(String categoryName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getCategory().getName().equalsIgnoreCase(categoryName)) {
                result.add(book);
            }
        }
        return result;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
        System.out.println("Okur eklendi: " + user.getName());
    }

    public void removeUser(String userID) {
        User user = users.remove(userID);
        if (user != null) {
            System.out.println("Okur çıkarıldı: " + user.getName());
        } else {
            System.out.println("Okur bulunamadı.");
        }
    }

    public User getUser(String userID) {
        return users.get(userID);
    }

    public void issueBook(String bookID, String userID) {
        User user = users.get(userID);
        Book book = books.get(bookID);
        if (user != null && book != null) {
            user.borrowBook(book);
        } else {
            System.out.println("Okur veya Kitap bulunamadı.");
        }
    }

    public void returnBook(String bookID, String userID) {
        User user = users.get(userID);
        Book book = books.get(bookID);
        if (user != null && book != null) {
            user.returnBook(book);
        } else {
            System.out.println("Okur veya Kitap bulunamadı.");
        }
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    public void listAllBooks() {
        System.out.println("Kitaplar:");
        for (Book book : books.values()) {
            System.out.println("-ID: " + book.getBookID() + " Kitap ismi: " + book.getName() + " Yazar: " + book.getAuthor() + " Kategori: " + book.getCategory());
        }
    }



    public void listAllUsers() {

        System.out.println("Okurlar:");
        for (User user : users.values()) {
            System.out.println("-ID: " + user.getId() + " İsim: " + user.getName());
        }
    }
}
