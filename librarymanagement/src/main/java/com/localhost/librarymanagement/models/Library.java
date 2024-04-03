package com.localhost.librarymanagement.models;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
    }

    // Getters and setters
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
    
    // Add a single book
    public void addBook(Book book) { this.books.add(book); }
}
