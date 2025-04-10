<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (request.getAttribute("issuedBooks") == null) {
        response.sendRedirect("BooksIssuedServlet?page=renew");
        return;
    }
%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Books Issued - Library Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/studentDashboardStyle.css">
    </head>
    <body>

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

        <div class="container mt-5 pt-5">
            <h2 class="text-center mb-4">Renew books you have issued</h2>

            <c:if test="${not empty studentId}">
                <div class="row">

                    <c:forEach var="bookData" items="${issuedBooks}">
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="card-title">Book ID: ${bookData.bookId}</h5>
                                </div>
                                <div class="card-body">
                                    <p><strong>Book Name:</strong> ${bookData.name}</p>
                                    <p><strong>Author:</strong> ${bookData.author}</p>
                                    <p><strong>Edition:</strong> ${bookData.edition}</p>
                                    <p><strong>Issue Date:</strong> ${bookData.issueDate}</p>
                                    <p><strong>Return Date:</strong> ${bookData.returnDate}</p>
                                </div>
                                <div class="card-footer">
                                    <form action="RenewalServlet" method="post">
                                        <input type="hidden" name="issue_id" value="${bookData.issueId}" />
                                        <button type="submit" class="btn btn-danger">
                                            <i class="fas fa-undo"></i> Renew Book
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <c:if test="${empty issuedBooks}">
                    <p>No books issued yet.</p>
                </c:if>
            </c:if>

            <c:if test="${empty studentId}">
                <p>Please log in to view issued books.</p>
            </c:if>

        </div>

        <footer class="bg-dark text-white text-center py-3 mt-5">
            <p>&copy; 2025 Library System. All rights reserved.</p>
        </footer>
    </body>
</html>
