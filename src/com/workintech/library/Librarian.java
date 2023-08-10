package com.workintech.library;



public class Librarian extends Person {
    private final Library library;

    public Librarian(String id, String name, String password, Library library) {
        super(id, name, password);
        this.library = library;
    }

    public boolean validate(String name, String password) {
        return this.getName().equals(name) && this.getPassword().equals(password);
    }
}