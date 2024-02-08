<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
</head>
<body>
<h1>Create New User</h1>
<form action="/api/user/delete" method="post">
    <label for="userid">user id:</label>
    <input type="text" id="userid" name="userId" required><br><br>


    <button type="submit">Delete user</button>
</form>
</body>
</html>
