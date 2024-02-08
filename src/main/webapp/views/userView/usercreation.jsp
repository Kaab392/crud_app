<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
</head>
<body>
<h1>Create New User</h1>
<form action="/api/user/" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="userName" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <button type="submit">Create User</button>
</form>
</body>
</html>
