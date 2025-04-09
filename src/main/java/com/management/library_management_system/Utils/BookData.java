 
package com.management.library_management_system.Utils;

import java.util.Date;

public class BookData {
    
    private int bookId;
    
    private int issueId;

    private String name;

    private String author;

    private String edition;

    private Date issueDate;

    private Date returnDate;

    
    public BookData() {
    }

    public BookData(int bookId, int issueId, String name, String author, String edition, Date issueDate, Date returnDate) {
        this.bookId = bookId;
        this.issueId = issueId;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    
    
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    
}
