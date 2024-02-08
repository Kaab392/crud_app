<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
</head>
<body>
<h1>Current User Details</h1>

<c:if test="${user.isPresent()}">
    <p>User ID: ${user.get().id}</p>
    <p>Name: ${user.get().userName}</p>
    <p>Email: ${user.get().email}</p>
    <!-- Add more details as needed -->
</c:if>

<h1>Update the current User Details</h1>

<c:choose>
    <c:when test="${user.isPresent()}">
        <form action="/api/user/update" method="post">
            <input type="hidden" name="id" value="${user.get().id}">
            <label for="userName">Name:</label>
            <input type="text" id="userName" name="userName" value="${user.get().userName}" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${user.get().email}" required><br>

            <!-- Add more input fields for other user attributes -->

            <button type="submit">Update User</button>
        </form>
    </c:when>
    <c:otherwise>
        <p>No user found</p>
    </c:otherwise>
</c:choose>

</body>
</html>
