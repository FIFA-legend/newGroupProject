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
</head>
<body>
<div>
    <c:forEach var="i" items="${requestScope.users}">
        <p>${i.name} ${i.surname} ${i.role}</p>
    </c:forEach>
</div>
<div><a href="${pageContext.request.contextPath}">To Home</a></div>
</body>
</html>

