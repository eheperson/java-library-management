package com.localhost.librarymanagement.main;

import java.util.List;

import com.localhost.librarymanagement.management.Management;
import com.localhost.librarymanagement.models.Book;
import com.localhost.librarymanagement.models.Issue;
import com.localhost.librarymanagement.models.Library;
import com.localhost.librarymanagement.models.Member;
import com.localhost.librarymanagement.utils.FileIO;
import com.localhost.librarymanagement.query.LibraryQuery;

public class Main {
    public static void main(String[] args) {
        Management libraryManagement = new Management();

        String[] libraryNames = new String[]{"L1"};
        // String[] libraryNames = new String[]{"L1", "L2", "L3"};  // experimental
        for (String libraryName : libraryNames) {
            List<String[]> booksData = FileIO.readCSV(libraryName + "_Books.csv");
            List<String[]> issuesData = FileIO.readCSV(libraryName + "_Issues.csv");
            
            Library library = new Library();
            for (String[] bookData : booksData) {
                try {
                    Book book = new Book(
                            /* ID       */ bookData[0],
                            /* Title    */ bookData[1],
                            /* Author   */ bookData[2],
                            /* Publisher*/ bookData[3],
                            /* Editor   */ bookData[4],
                            /* Genre    */ bookData[5],
                            /* Quantity */ Integer.parseInt(bookData[6])
                    );
                    library.addBook(book);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing integer from string: " + bookData[6]);
                    e.printStackTrace();
                }
            }
            
            libraryManagement.addLibrary(library);
            
            List<Member> members = FileIO.readMembers("Members.csv");
            for (String[] issueData : issuesData) {
                Issue issue = FileIO.createIssueFromData(issueData, members, library.getBooks());
                libraryManagement.addIssue(0, issue);
                // Uncomment below if you have L2_Issues.csv, L2_Books.csv and L3_Issues.csv, L3_Books.csv etc.
                // libraryManagement.addIssue(libraryNames.length, issue);
            }
        }

        LibraryQuery libraryQuery = new LibraryQuery(libraryManagement);
        
        Book mostIssuedBook = libraryQuery.findMostIssuedBook();
        if (mostIssuedBook != null) {
            System.out.println("Most issued book: " + mostIssuedBook.getTitle());
        } else {
            System.out.println("No books have been issued.");
        }
        
        Member memberWithMostIssues = libraryQuery.findMemberWithMostIssues();
        if (memberWithMostIssues != null) {
            System.out.println("Member who issues the most books: " + memberWithMostIssues.getName());
        } else {
            System.out.println("No member data available.");
        }

        Member memberWithLeastIssues = libraryQuery.findMemberWithLeastIssuesFromCSLibrary();
        if (memberWithLeastIssues != null) {
            System.out.println("Member who issues the least number of books: " + memberWithLeastIssues.getName());
        } else {
            System.out.println("All members have issued books equally or no issues recorded.");
        }

        Book bookWithMostCopies = libraryQuery.findBookWithMostCopies();
        if (bookWithMostCopies != null) {
            System.out.println("Book with the most copies: " + bookWithMostCopies.getTitle());
        } else {
            System.out.println("No books available.");
        }
        
        Book bookWithFewestCopiesOfIssuedBooks = libraryQuery.findBookWithFewestCopiesOfIssuedBooks();
        if (bookWithFewestCopiesOfIssuedBooks != null) {
            System.out.println("Book with the fewest copies of previously issued books: " + bookWithFewestCopiesOfIssuedBooks.getTitle());
        } else {
            System.out.println("No books available.");
        }
        
        Member memberWithLeastIssuesFromCSLibrary = libraryQuery.findMemberWithLeastIssuesFromCSLibrary();
        if (memberWithLeastIssuesFromCSLibrary != null) {
            System.out.println("Member who issues the least number of books from the Computer Science Library: " + memberWithLeastIssuesFromCSLibrary.getName());
        } else {
            System.out.println("No members available.");
        }
        
        System.out.println("Highest penalty for late returning: " + libraryQuery.calculateHighestPenalty() + " TL");
    }
}
