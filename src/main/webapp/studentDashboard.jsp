<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Library Dashboard</title>
         
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
         
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
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
                            <a class="nav-link nav-link-custom btn-primary-custom" href="booksIssued.jsp"><i class="fas fa-book"></i> Books Issued</a>
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
                            <a class="nav-link nav-link-custom btn-danger-custom" href="returnBook.jsp"><i class="fas fa-undo"></i> Return Book</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn-danger-custom" id="logoutBtn" href="logout.jsp"><i class="fas fa-sign-out-alt"></i> Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        
        <div class="container mt-5 text-center">
            <h1>Welcome to the Library Dashboard</h1>
            <p class="lead">Easily manage your books and library transactions</p>
        </div>

       
        <div class="container mt-5">
            <div class="row text-center">
                 
                <div class="col-md-4">
                    <div class="card card-bg-primary">
                        <div class="card-body">
                            <h5 class="card-title">Books Issued</h5>
                            <p class="card-text">View the list of books you have issued, along with their return dates.</p>
                            <a href="booksIssued.jsp" class="btn btn-light btn-custom"><i class="fas fa-book"></i> View Issued Books</a>
                        </div>
                    </div>
                </div>
 
                <div class="col-md-4">
                    <div class="card card-bg-success">
                        <div class="card-body">
                            <h5 class="card-title">Search Book</h5>
                            <p class="card-text">Search for books by title or author.</p>
                            <a href="search.jsp" class="btn btn-light btn-custom"><i class="fas fa-search"></i> Search for Books</a>
                        </div>
                    </div>
                </div>
 
                <div class="col-md-4">
                    <div class="card card-bg-info">
                        <div class="card-body">
                            <h5 class="card-title">Issue Book</h5>
                            <p class="card-text">Choose a book and issue it to your account.</p>
                            <a href="issueBook.jsp" class="btn btn-light btn-custom"><i class="fas fa-plus"></i> Issue a Book</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row text-center mt-4">
                
                <div class="col-md-4">
                    <div class="card card-bg-warning">
                        <div class="card-body">
                            <h5 class="card-title">Renew Book</h5>
                            <p class="card-text">Extend the return date for books you have issued.</p>
                            <a href="renewBook.jsp" class="btn btn-light btn-custom"><i class="fas fa-sync"></i> Renew Book</a>
                        </div>
                    </div>
                </div>
 
                <div class="col-md-4">
                    <div class="card card-bg-danger">
                        <div class="card-body">
                            <h5 class="card-title">Return Book</h5>
                            <p class="card-text">Return the books you've borrowed from the library.</p>
                            <a href="returnBook.jsp" class="btn btn-light btn-custom"><i class="fas fa-undo"></i> Return Book</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
 
        <footer class="bg-dark text-white text-center py-4 mt-5">
            <p>&copy; 2025 Library System. All rights reserved.</p>
        </footer>
 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
