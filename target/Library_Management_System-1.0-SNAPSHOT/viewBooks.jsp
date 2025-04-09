<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Check if bookList is not set and redirect to the servlet --%>
<%
    if (request.getAttribute("bookList") == null) {
        response.sendRedirect("GetAllBooksServlet");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View All Books - Library Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/adminDashboardStyle.css">
    <link rel="stylesheet" type="text/css" href="css/viewBooksStyle.css">
</head>
<body>
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-custom fixed-top">
                    <a class="navbar-brand" href="adminDashboard.jsp">Library Admin</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a class="nav-link active" href="adminDashboard.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="addBook.jsp"><i class="fas fa-book-medical"></i> Add Books</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="GetAllBooksServlet"><i class="fas fa-eye"></i> View Books</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="viewBooks.jsp"><i class="fas fa-trash-alt"></i> Delete Books</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="viewBooks.jsp"><i class="fas fa-edit"></i> Edit Books</a>
                            </li>

                            <li class="nav-item" id="logoutBtn">
                                <a class="nav-link logout-btn d-flex align-items-center justify-content-center px-3 py-2 rounded" href="logout.jsp">
                                    <i class="fas fa-sign-out-alt me-2"></i> Logout
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>

        <div class="container mt-4">
            <h2 class="text-center mb-4">All Books</h2>
            <div class="table-container">
                <table class="table table-hover table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Book ID</th>
                            <th>Book Name</th>
                            <th>Author</th>
                            <th>Edition</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${bookList}">
                            <tr>
                                <td>${book.bookId}</td>
                                <td>${book.name}</td>
                                <td>${book.author}</td>
                                <td>${book.edition}</td>
                                <td>${book.quantity}</td>
                                <td class="action-btns">
                                    <a href="editBook.jsp?book_id=${book.bookId}" class="btn btn-primary btn-sm">
                                        <i class="fa fa-pencil"></i> Edit
                                    </a>
                                    <form action="BookServlet" method="POST" class="d-inline" onsubmit="return confirm('Are you sure you want to delete this book?');">
                                        <input type="hidden" name="action" value="deleteBook">
                                        <input type="hidden" name="book_id" value="${book.bookId}">
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            <i class="fa fa-trash"></i> Delete
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
