<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
</head>
<body>
<h1>Create New User</h1>
<form action="/api/user/userViewById" method="get">
    <label for="id">user id:</label>
    <input type="text" id="id" name="id" required><br><br>


    <button type="submit">View user Details</button>
</form>
</body>
</html>
