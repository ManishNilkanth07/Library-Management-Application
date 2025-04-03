package com.management.library_management_system.model;

public class Student {

    private int studentId;
    
    private String name;
    
    private String email;
    
    private String password;
    
    private String role;
    
    private String membershipNumber;

    
    private Student(StudentBuilder builder) {
        this.studentId = builder.studentId;
        
        this.name = builder.name;
        
        this.email = builder.email;
        
        this.password = builder.password;
        
        this.role = builder.role;
        
        this.membershipNumber = builder.membershipNumber;
    }

   
    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", membershipNumber='" + membershipNumber + '\'' +
                '}';
    }

    // Static nested Builder class
    public static class StudentBuilder {

        private int studentId;
        private String name;
        private String email;
        private String password;
        private String role;
        private String membershipNumber;

        // Setter methods that return the builder for chaining
        public StudentBuilder setStudentId(int studentId) {
            this.studentId = studentId;
            return this;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public StudentBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public StudentBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public StudentBuilder setMembershipNumber(String membershipNumber) {
            this.membershipNumber = membershipNumber;
            return this;
        }

        // Build method to create a Student object
        public Student build() {
            return new Student(this);
        }
    }
}
