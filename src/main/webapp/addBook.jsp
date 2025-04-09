<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Book - Library Management</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/adminDashboardStyle.css">
    <style>
        .navbar-custom {
            background-color: #2c3e50;
        }
        .navbar-custom .navbar-brand, .navbar-custom .nav-link {
            color: #ecf0f1;
        }
        .navbar-custom .nav-link:hover {
            color: #3498db;
        }
        .form-control {
            margin-bottom: 1.5rem;
        }
        .btn-primary-custom {
            background-color: #3498db;
            border-color: #2980b9;
        }
        .btn-primary-custom:hover {
            background-color: #2980b9;
            border-color: #1c4f74;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-custom fixed-top">
                    <a class="navbar-brand" href="BookStatsServlet">Library Admin</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a class="nav-link active" href="BookStatsServlet"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
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

        <!-- Add Book Form -->
        <div class="container mt-4">
            <h2 class="text-center mb-4">Add a New Book</h2>
            <div class="card shadow-sm">
                <div class="card-body">
                    <form action="BookServlet" method="POST">
                        <input type="hidden" name="action" value="addBook">
                        <div class="mb-3">
                            <label for="name" class="form-label">Book Name</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="author" class="form-label">Author</label>
                            <input type="text" class="form-control" id="author" name="author" required>
                        </div>
                        <div class="mb-3">
                            <label for="edition" class="form-label">Edition</label>
                            <input type="text" class="form-control" id="edition" name="edition" required>
                        </div>
                        <div class="mb-3">
                            <label for="quantity" class="form-label">Quantity</label>
                            <input type="number" class="form-control" id="quantity" name="quantity" required>
                        </div>
                        <button type="submit" class="btn btn-primary-custom">Add Book</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
