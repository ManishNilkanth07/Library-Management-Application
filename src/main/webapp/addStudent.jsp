<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Registration</title>
    </head>
    <body>
        <div class="container">
            <h2>Student Registration Form</h2>

            <!-- Student Registration Form -->
            <form action="AdminRegistration" method="post">

                <!-- Name -->
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <!-- Email -->
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <!-- Password -->
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div
                
                <!-- Library_Name --> 
                <div class="form-group">
                    <label for="libraryName">library Name</label>
                    <input type="text" id="libraryName" name="libraryName" required>
                </div>

                  
                <div class="form-group">
                    <label for="libraryName">address</label>
                    <input type="text" id="address" name="address" required>
                </div>
                  
                <!-- Role -->
                <div class="form-group">
                    <label for="role">Role:</label>
                    <select id="role" name="role" required>
                        <option value="student">student</option>
                        <option value="admin">admin</option>
                    </select>
                </div>

                <div class="form-group">
                    <button type="submit">Register</button>
                </div>
            </form>

        </div>
    </body>
</html>
