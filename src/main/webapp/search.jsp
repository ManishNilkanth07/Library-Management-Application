<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDate" %>
<%
    String today = LocalDate.now().toString();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Books</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/searchBookStyle.css">
    <link rel="stylesheet" type="text/css" href="css/studentDashboardStyle.css">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="studentDashboard.jsp">Library Management</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>

<div class="container mt-5">
    <div class="card search-section">
        <h3 class="text-center mb-4">Search for Books</h3>
        <form action="SearchBooks" method="get">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="name">Book Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Enter book name">
                </div>
                <div class="form-group col-md-6">
                    <label for="author">Author Name</label>
                    <input type="text" class="form-control" id="author" name="author" placeholder="Enter author name">
                </div>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Search</button>
        </form>
    </div>

    <c:if test="${not empty books}">
        <div class="card mt-5">
            <h3 class="text-center card-header bg-info text-white">Search Results</h3>
            <div class="container mt-5">
                <h2 class="text-center mb-4">Issue Book</h2>

                <div class="row">
                    <c:forEach var="book" items="${books}">
                        <div class="col-md-4 mb-4">
                            <div class="card shadow-sm">
                                <img src="images/book.jpg" class="card-img-top" alt="Book Image">
                                <div class="card-body">
                                    <h5 class="card-title">${book.name}</h5>
                                    <p class="card-text"><strong>Author:</strong> ${book.author}</p>
                                    <p class="card-text"><strong>Edition:</strong> ${book.edition}</p>
                                    <p class="card-text"><strong>Quantity:</strong> ${book.quantity}</p>

                                    <form action="IssueServlet" method="post" onsubmit="return validateDates('${book.bookId}')">
                                        <input type="hidden" name="book_id" value="${book.bookId}">

                                        <div class="mb-2">
                                            <label for="issuedDate_${book.bookId}" class="form-label">Issued Date:</label>
                                            <input type="date" class="form-control"
                                                   id="issuedDate_${book.bookId}"
                                                   name="issued_date"
                                                   required
                                                   min="<%= today %>"
                                                   max="<%= today %>"
                                                   onchange="setReturnMinDate('${book.bookId}')">
                                        </div>

                                        <div class="mb-3">
                                            <label for="returnDate_${book.bookId}" class="form-label">Return Date:</label>
                                            <input type="date" class="form-control"
                                                   id="returnDate_${book.bookId}"
                                                   name="return_date"
                                                   required>
                                        </div>

                                        <button type="submit" class="btn btn-primary btn-sm">Issue Book</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${empty books}">
        <div class="alert alert-warning mt-4" role="alert">
            <i class="fas fa-search"></i> No books found for the given criteria. Please try again!
        </div>
    </c:if>
</div>

<script>
    function setReturnMinDate(bookId) {
        const issuedInput = document.getElementById('issuedDate_' + bookId);
        const returnInput = document.getElementById('returnDate_' + bookId);
        const issuedDate = new Date(issuedInput.value);

        if (isNaN(issuedDate)) return;

        let maxDate = new Date(issuedDate);
        maxDate.setDate(maxDate.getDate() + 8);

        const minDateStr = issuedInput.value;
        const maxDateStr = maxDate.toISOString().split('T')[0];

        returnInput.min = minDateStr;
        returnInput.max = maxDateStr;
    }

    function validateDates(bookId) {
        const issuedInput = document.getElementById('issuedDate_' + bookId);
        const returnInput = document.getElementById('returnDate_' + bookId);

        if (returnInput.value < issuedInput.value) {
            alert("Return date cannot be before Issued date.");
            return false;
        }

        const issuedDate = new Date(issuedInput.value);
        const returnDate = new Date(returnInput.value);
        const maxReturnDate = new Date(issuedDate);
        maxReturnDate.setDate(issuedDate.getDate() + 8);

        if (returnDate > maxReturnDate) {
            alert("Return date must be within 8 days from the issued date.");
            return false;
        }

        return true;
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
