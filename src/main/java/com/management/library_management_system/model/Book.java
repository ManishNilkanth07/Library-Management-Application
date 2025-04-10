package com.management.library_management_system.model;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Book", urlPatterns = {"/Book"})
public class Book {

    private int bookId;

    private String name;

    private String author;

    private String edition;

    private int quantity;

    public Book(BookBuilder bookBuilder) {

        this.bookId = bookBuilder.bookId;
        this.name = bookBuilder.name;
        this.author = bookBuilder.author;
        this.edition = bookBuilder.edition;
        this.quantity = bookBuilder.quantity;
    }

    public int getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }

    public int getQuantity() {
        return quantity;
    }

    public static class BookBuilder {

        private int bookId;

        private String name;

        private String author;

        private String edition;

        private int quantity;

        public BookBuilder setBookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public BookBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BookBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder setEdition(String edition) {
            this.edition = edition;
            return this;
        }

        public BookBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Book build() {
            return new Book(this);
        }

    }

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", name=" + name + ", author=" + author + ", edition=" + edition + ", quantity=" + quantity + '}';
    }

}
