
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 21.04.2020
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Car's list</title>
</head>
<body>
<div>
    <c:forEach var="i" items="${requestScope.cars}">
        <p>price: ${i.price}<br>Number: ${i.number}<br>Amount: ${i.regions.size()}</p>
        <hr>
    </c:forEach>
</div>
<div><a href="${pageContext.request.contextPath}">To Home</a></div>
</body>
</html>
