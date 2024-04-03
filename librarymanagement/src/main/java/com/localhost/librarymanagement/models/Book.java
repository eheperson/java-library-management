package com.localhost.librarymanagement.models;

public class Book {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private String edition;
    private String genre;
    private int quantity;

    // Constructor
    public Book(String id, String title, String author, String publisher, String edition, String genre, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.edition = edition;
        this.genre = genre;
        this.quantity = quantity;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public String getEdition() { return edition; }
    public String getGenre() { return genre; }
    public int getQuantity() { return quantity; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setEdition(String edition) { this.edition = edition; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
