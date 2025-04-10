<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Library Management System</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/indexHomeStyle.css">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </head>

    <body>

        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="#">Library Management</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="studentLogin.jsp">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="studentRegistration.jsp">Register</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="jumbotron text-center my-5">
            <h1>Welcome to Our Library!</h1>
            <p class="lead">Discover a world of knowledge and books. Manage your account, borrow and return books seamlessly.</p>
            <hr class="my-4">
            <p>Our library management system helps you manage your library resources with ease.</p>
        </div>


        <div class="container role-selection my-5">
            <h2 class="text-center mb-4">Choose Your Role</h2>
            <div class="row">
                <div class="col-md-6 text-center">
                    <a class="btn btn-primary btn-lg" href="studentRegistration.jsp" role="button">Register as Student</a>
                </div>
                <div class="col-md-6 text-center">
                    <a class="btn btn-secondary btn-lg" href="adminRegistration.jsp" role="button">Register as Admin</a>
                </div>
            </div>
        </div>


        <footer class="footer text-center">
            <p>&copy; 2025 Library Management System. All rights reserved.</p>
        </footer>

    </body>
</html>
