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
    <style>
        body {
            background-color: #f7f7f7;
            font-family: 'Arial', sans-serif;
        }

        .error-container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            flex-direction: column;
            text-align: center;
        }

        .error-box {
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
        }

        .error-code {
            font-size: 120px;
            font-weight: bold;
            color: #f44336;
        }

        .error-message {
            font-size: 22px;
            color: #333;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        .alert {
            display: inline-block;
            max-width: 80%;
            text-align: center;
            margin-top: 20px;
        }

        .back-home-link {
            text-decoration: none;
            font-weight: 600;
            color: #007bff;
        }

        .back-home-link:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

    <div class="error-container">
        <div class="error-box">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">Something Went Wrong!</h4>
                <!-- Display error code dynamically -->
                <p class="error-code">${statusCode}</p>
                <p class="error-message">${errorMessage != null ? errorMessage : 'Sorry, we encountered an unexpected issue. Please try again later.'}</p>
                <hr>
                <p>If you need further assistance, please contact support or try again later.</p>
            </div>

            <!-- Back to Home Button -->
            <a href="index.jsp" class="btn btn-primary">Go Back to Home</a>
        </div>
    </div>
 
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>
