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
        <p>price: ${i.price}</p>
        <p>Number: ${i.number}</p>
    </c:forEach>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
