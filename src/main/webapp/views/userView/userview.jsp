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
<c:choose>
<c:when test="${user.isPresent()}">
<p><b> User ID : </b> ${user.get().id}</p>
<p><b> Name: </b> ${user.get().userName}</p>
<p><b>Email: </b> ${user.get().email}</p>
<!-- Add more details as needed -->
</c:when>
<c:otherwise>
<p>No user found</p>
</c:otherwise>
</c:choose>
