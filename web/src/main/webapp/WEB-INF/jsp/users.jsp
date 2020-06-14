<%--
  Created by IntelliJ IDEA.
  User: kolod
  Date: 19.04.2020
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User's List</title>
    <style>
        <%@ include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="center">
    <c:forEach var="i" items="${users}">
        <p>Name: ${i.name}</p>
        <p>Surname: ${i.surname}</p>
        <p>Role: ${i.role}</p>
        <hr>
    </c:forEach>
<%@ include file="footer.jsp"%>
</html>

