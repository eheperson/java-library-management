package com.localhost.librarymanagement.utils;

import com.localhost.librarymanagement.models.Book;
import com.localhost.librarymanagement.models.Issue;
import com.localhost.librarymanagement.models.Member;

import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
// import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FileIO {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    public static List<String[]> readCSV(String filename) {
        List<String[]> records = new ArrayList<>();
        InputStream inputStream = FileIO.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + filename);
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                    FileIO.class.getClassLoader().getResourceAsStream(filename),
                    StandardCharsets.UTF_8))) {
            br.readLine(); // Skip header line
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("^\"|\"$", ""); // Remove surrounding quotes if present
                }
                records.add(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return records;
    }

    public static List<Member> readMembers(String filename) {
        List<String[]> records = readCSV(filename);
        List<Member> members = new ArrayList<>();
        for (String[] record : records) {
            if (record.length < 3) {
                System.err.println("Invalid member record: " + String.join(", ", record));
                continue;
            }
            members.add(new Member(record[0], record[1], record[2]));
        }
        return members;
    }

    public static Issue createIssueFromData(String[] issueData, List<Member> members, List<Book> books) {
        if (issueData.length < 5) {
            System.err.println("Invalid issue record: " + String.join(", ", issueData));
            return null;
        }

        String issueID = issueData[0];
        String memberID = issueData[1];
        String bookID = issueData[2];
        Date issueDate = parseDate(issueData[3]);
        Date returnDate = parseDate(issueData[4]);

        Member member = members.stream()
                .filter(m -> m.getId().equals(memberID))
                .findFirst().orElse(null);
        Book book = books.stream()
                .filter(b -> b.getId().equals(bookID))
                .findFirst().orElse(null);

        if (member == null || book == null) {
            System.err.println("Issue record references non-existent member or book: " + issueID);
            return null;
        }

        return new Issue(issueID, member, book, issueDate, returnDate);
    }

    private static Date parseDate(String dateString) {
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + dateString);
            return null;
        }
    }
}
