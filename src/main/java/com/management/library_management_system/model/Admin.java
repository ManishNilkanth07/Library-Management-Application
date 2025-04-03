package com.management.library_management_system.model;

 
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Admin", urlPatterns = {"/Admin"})
public class Admin {

    private int adminId;
    private String name;
    private String email;
    private String password;
    private String role;
    private String address;
    private String membershipNumber;
    private String libraryName;

    // Private constructor to prevent direct instantiation from outside the Builder
    private Admin(AdminBuilder builder) {
        this.adminId = builder.adminId;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.address = builder.address;
        this.membershipNumber = builder.membershipNumber;
        this.libraryName = builder.libraryName;
    }

     
    public int getAdminId() {
        return adminId;
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

    public String getAddress() {
        return address;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public String getLibraryName() {
        return libraryName;
    }

    
    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", address='" + address + '\'' +
                ", membershipNumber='" + membershipNumber + '\'' +
                ", libraryName='" + libraryName + '\'' +
                '}';
    }

    // Static nested Builder class
    public static class AdminBuilder {

        private int adminId;
        private String name;
        private String email;
        private String password;
        private String role;
        private String address;
        private String membershipNumber;
        private String libraryName;

        // Setter methods that return the builder for chaining
        public AdminBuilder setAdminId(int adminId) {
            this.adminId = adminId;
            return this;
        }

        public AdminBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AdminBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public AdminBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public AdminBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public AdminBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public AdminBuilder setMembershipNumber(String membershipNumber) {
            this.membershipNumber = membershipNumber;
            return this;
        }

        public AdminBuilder setLibraryName(String libraryName) {
            this.libraryName = libraryName;
            return this;
        }

     
        public Admin build() {
            return new Admin(this);
        }
    }
}
