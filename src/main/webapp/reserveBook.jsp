<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (request.getAttribute("bookList") == null) {
        response.sendRedirect("GetAllBooksServlet?page=reserve");
        return;
    }
    String today = LocalDate.now().toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserve Book - Library Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/viewBooksStyle.css">
    <link rel="stylesheet" type="text/css" href="css/studentDashboardStyle.css">
</head>
<body>
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="studentDashboard.jsp">Library Management</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link nav-link-custom btn-primary-custom" href="BooksIssuedServlet"><i class="fas fa-book"></i> Books Issued</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-custom btn-success-custom" href="search.jsp"><i class="fas fa-search"></i> Search Book</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-custom btn-info-custom" href="issueBook.jsp"><i class="fas fa-plus"></i> Issue Book</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-custom btn-warning-custom" href="renewBook.jsp"><i class="fas fa-sync"></i> Renew Book</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-custom btn-danger-custom" href="BooksIssuedServlet"><i class="fas fa-undo"></i> Return Book</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn-danger-custom" id="logoutBtn" href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container mt-5">
            <h2 class="text-center mb-4">Reserve Book</h2>

            <div class="row">
                <c:forEach var="book" items="${bookList}">
                    <div class="col-md-4 mb-4">
                        <div class="card shadow-sm">
                            <img src="images/book.jpg" class="card-img-top" alt="Book Image">
                            <div class="card-body">
                                <h5 class="card-title">${book.name}</h5>
                                <p class="card-text"><strong>Author:</strong> ${book.author}</p>
                                <p class="card-text"><strong>Edition:</strong> ${book.edition}</p>
                                <p class="card-text"><strong>Quantity:</strong> ${book.quantity}</p>

                                <form action="ReservationServlet" method="post">
                                    <input type="hidden" name="book_id" value="${book.bookId}">
                                    <button type="submit" class="btn btn-primary btn-sm">Reserve Book</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
