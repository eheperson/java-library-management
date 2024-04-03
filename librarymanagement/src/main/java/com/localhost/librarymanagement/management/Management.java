package com.localhost.librarymanagement.management;

import com.localhost.librarymanagement.models.Issue;
import com.localhost.librarymanagement.models.Library;

import java.util.ArrayList;
import java.util.List;


public class Management {
    private List<Library> libraries;
    private List<List<Issue>> issues;

    public Management() {
        this.libraries = new ArrayList<>();
        this.issues = new ArrayList<>();
    }

    public void addLibrary(Library library) {
        libraries.add(library);
        issues.add(new ArrayList<>());
    }
    
    public void addIssue(int libraryIndex, Issue issue) {
        if (libraryIndex >= 0 && libraryIndex < issues.size()) {
            issues.get(libraryIndex).add(issue);
        }
    }
    
    public List<Library> getLibraries() { return libraries; }
    public List<List<Issue>> getIssues() { return issues; }
    
    public void setLibraries(List<Library> libraries) { this.libraries = libraries; }
    public void setIssues(List<List<Issue>> issues) { this.issues = issues; }
}
