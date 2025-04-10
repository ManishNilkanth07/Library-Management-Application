<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error - Library Management</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/errorPageStyle.css">
    </head>
    <body>
        <div class="error-container">
            <div class="error-box">
                <div class="alert alert-danger" role="alert">
                    <h4 class="alert-heading">Something Went Wrong!</h4>
                    <p class="error-code">${statusCode}</p>
                    <p class="error-message">${errorMessage != null ? errorMessage : 'Sorry, we encountered an unexpected issue. Please try again later.'}</p>
                    <hr>
                    <p>If you need further assistance, please contact support or try again later.</p>
                </div>
                <a href="index.jsp" class="btn btn-primary">Go Back to Home</a>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
