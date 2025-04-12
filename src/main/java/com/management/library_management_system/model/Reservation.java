package com.management.library_management_system.model;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private int studentId;
    private int bookId;
    private LocalDateTime createdAt;

    private Reservation(ReservationBuilder builder) {
        this.id = builder.id;
        this.studentId = builder.studentId;
        this.bookId = builder.bookId;
        this.createdAt = builder.createdAt;
    }



    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static class ReservationBuilder {
        private int id;
        private int studentId;
        private int bookId;
        private LocalDateTime createdAt;

        public ReservationBuilder() {}

        public ReservationBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ReservationBuilder studentId(int studentId) {
            this.studentId = studentId;
            return this;
        }

        public ReservationBuilder bookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public ReservationBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }
}
