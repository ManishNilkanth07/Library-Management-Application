
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Library Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/adminDashboardStyle.css">

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

        <div class="container mt-5">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="text-center mb-4">Welcome to the Library Management System</h2>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-4 mb-4">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Add Books</h5>
                            <p class="card-text">Add new books to the library collection.</p>
                            <a href="addBook.jsp" class="btn btn-primary-custom">Add Now</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 mb-4">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">View Books</h5>
                            <p class="card-text">Browse and view all available books.</p>
                            <a href="viewBooks.jsp" class="btn btn-info">View Books</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 mb-4">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Delete Books</h5>
                            <p class="card-text">Delete books from the library collection.</p>
                            <a href="viewBooks.jsp" class="btn btn-warning">Delete Now</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6 mb-4">
                    <div class="card shadow-sm">
                        <div class="card-body text-center">
                            <h3>Total Books</h3>
                            <p class="display-4">${totalBooks}</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 mb-4">
                    <div class="card shadow-sm">
                        <div class="card-body text-center">
                            <h3>Books Issued</h3>
                            <p class="display-4">${issuedBooks}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
