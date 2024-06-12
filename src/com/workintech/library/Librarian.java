package com.workintech.library;



public class Librarian extends Person {

    public Librarian(String id, String name, String password) {
        super(id, name, password);
    }

    public boolean validate(String name, String password) {
        return this.getName().equals(name) && this.getPassword().equals(password);
    }
}