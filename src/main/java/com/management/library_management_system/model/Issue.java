package com.management.library_management_system.model;

import java.util.Date;

public class Issue {

    private int issueId;

    private int bookId;

    private int studentId;

    private Date issueDate;

    private Date returnDate;

    private Issue(IssueBuilder builder) {
        
        this.issueId = builder.issueId;
        
        this.bookId = builder.bookId;
        
        this.studentId = builder.studentId;
        
        this.issueDate = builder.issueDate;
       
        this.returnDate = builder.returnDate;
    }

    public int getIssueId() {
        return issueId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "Issue{" + "issueId=" + issueId + ", bookId=" + bookId + ", studentId=" + studentId + ", issueDate=" + issueDate + ", returnDate=" + returnDate + '}';
    }

    public static class IssueBuilder {

        private int issueId;

        private int bookId;

        private int studentId;

        private Date issueDate;

        private Date returnDate;

        public IssueBuilder setIssueId(int issueId) {
            this.issueId = issueId;
            return this;
        }

        public IssueBuilder setBookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public IssueBuilder setStudentId(int studentId) {
            this.studentId = studentId;
            return this;
        }

        public IssueBuilder setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public IssueBuilder setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }
        
        public Issue build()
        {
            return new Issue(this);
        }
        
    }
}
