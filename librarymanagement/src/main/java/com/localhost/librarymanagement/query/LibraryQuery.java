package com.localhost.librarymanagement.query;

import com.localhost.librarymanagement.management.Management;
import com.localhost.librarymanagement.models.Book;
import com.localhost.librarymanagement.models.Issue;
import com.localhost.librarymanagement.models.Member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.Optional;
import java.util.concurrent.TimeUnit;
// import java.util.stream.Collectors;

public class LibraryQuery {
    private Management libraryManagement;

    public LibraryQuery(Management libraryManagement) {
        this.libraryManagement = libraryManagement;
    }

    public Book findMostIssuedBook() {
        Map<Book, Integer> bookIssueCount = new HashMap<>();
        for (List<Issue> issueList : libraryManagement.getIssues()) {
            for (Issue issue : issueList) {
                bookIssueCount.put(issue.getBook(), bookIssueCount.getOrDefault(issue.getBook(), 0) + 1);
            }
        }

        return bookIssueCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Member findMemberWithMostIssues() {
        Map<Member, Integer> memberIssueCount = new HashMap<>();
        for (List<Issue> issueList : libraryManagement.getIssues()) {
            for (Issue issue : issueList) {
                memberIssueCount.put(issue.getMember(), memberIssueCount.getOrDefault(issue.getMember(), 0) + 1);
            }
        }

        return memberIssueCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public double calculateHighestPenalty() {
        double highestPenalty = 0.0;
        for (List<Issue> issueList : libraryManagement.getIssues()) {
            for (Issue issue : issueList) {
                long daysOverdue = getDaysBetween(issue.getReturningDate(), new Date());
                if (daysOverdue > 14) {
                    double penalty = (daysOverdue - 14) * 0.50;
                    if (penalty > highestPenalty) {
                        highestPenalty = penalty;
                    }
                }
            }
        }
        return highestPenalty;
    }

    public Book findBookWithMostCopies() {
        return libraryManagement.getLibraries().stream()
                .flatMap(library -> library.getBooks().stream())
                .max((b1, b2) -> Integer.compare(b1.getQuantity(), b2.getQuantity()))
                .orElse(null);
    }

    public Book findBookWithFewestCopiesOfIssuedBooks() {
        Map<Book, Integer> bookQuantityMap = new HashMap<>();
        libraryManagement.getLibraries().stream()
                .flatMap(library -> library.getBooks().stream())
                .forEach(book -> bookQuantityMap.put(book, book.getQuantity()));

        libraryManagement.getIssues().forEach(issueList -> issueList.forEach(issue -> {
            if (bookQuantityMap.containsKey(issue.getBook())) {
                bookQuantityMap.put(issue.getBook(), bookQuantityMap.get(issue.getBook()) - 1);
            }
        }));

        return bookQuantityMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    public Member findMemberWithLeastIssuesFromCSLibrary() {
        if (!libraryManagement.getIssues().isEmpty()) {
            List<Issue> libraryIssues = libraryManagement.getIssues().get(0);
            Map<Member, Integer> memberIssueCount = new HashMap<>();
            for (Issue issue : libraryIssues) {
                memberIssueCount.put(issue.getMember(), memberIssueCount.getOrDefault(issue.getMember(), 0) + 1);
            }
    
            return memberIssueCount.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
        } else {
            return null;
        }
    }

    private long getDaysBetween(Date startDate, Date endDate) {
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
