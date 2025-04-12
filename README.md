📚 Library Management System

This project is an online Library Management System built using Java (Core & Advanced), SQL, and the MVC architecture. It is designed for both internal and external users, offering secure login and role-based access for library administrators and students.

------------------------------------------------------------

🚀 Features

🧑‍💼 Admin Module (Head)

Admin Registration
- Name, Library Name, Address, Email (valid), Role, Password (minimum 8 characters, including special symbols)
- Confirmation via email with membership number and password

Admin Dashboard
- Add books (name, author, edition, quantity, parking slot field)
- View all books
- Issue books to students
- Edit or delete book entries

------------------------------------------------------------

👨‍🎓 Student Module

Student Registration
- Name, Email (valid), Role, Password (minimum 8 characters with special symbol)
- Confirmation via email with membership number and password

Student Dashboard
- View list of issued books with return dates
- Search for books by title or author
- Issue available books
- Renew books (only if not reserved by others)
- Return issued books

------------------------------------------------------------

🛠️ Technologies Used

Layer        : Technology
UI / Backend : Java (Core & Advanced)
Database     : SQL
Architecture : MVC (Model-View-Controller)

------------------------------------------------------------

📌 Key Functionalities

Role-based authentication  
Email notifications on registration and book issue  
Book search with dynamic filtering  
Automatic book return and reservation handling  
Admin control over library inventory
