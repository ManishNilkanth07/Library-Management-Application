<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Books Issued - Library Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-custom navbar-dark fixed-top">
            <a class="navbar-brand" href="studentDashboard.jsp">Library Management</a>
        </nav>

        <div class="container mt-5 pt-5">
            <h2 class="text-center mb-4">Books You Have Issued</h2>

            <c:set var="studentId" value="${sessionScope.studentId}" />

            
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
                                  
                                    <button class="btn btn-success" onclick="window.location.href = 'returnBook.jsp?issueId=${bookData.issueId}&studentId=${studentId}'">
                                        Return Book
                                    </button>
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
