package com.localhost.librarymanagement.models;

import java.util.Date;

public class Issue {
    private String id;
    private Member member;
    private Book book;
    private Date issueDate;
    private Date returningDate;

    // Constructor
    public Issue(String id, Member member, Book book, Date issueDate, Date returningDate) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.issueDate = issueDate;
        this.returningDate = returningDate;
    }

    // Getters
    public String getId() { return id; }
    public Member getMember() { return member; }
    public Book getBook() { return book; }
    public Date getIssueDate() { return issueDate; }
    public Date getReturningDate() { return returningDate; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setMember(Member member) { this.member = member; }
    public void setBook(Book book) { this.book = book; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
    public void setReturningDate(Date returningDate) { this.returningDate = returningDate; }
}
