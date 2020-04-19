<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User's List</title>
</head>
<body>
    <div align="center">
        <c:forEach var="i" items="${requestScope.users}">
            <p>${i.name} ${i.surname} ${i.role}</p>
        </c:forEach>
    </div>
</body>
</html>