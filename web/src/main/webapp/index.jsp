<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
    <style>
      <%@ include file="WEB-INF/static/css/style.css"%>
    </style>
  </head>
  <body>
<p>
  <a href="${pageContext.request.contextPath}/users" class="check-all">Show all users</a>
</p>
<p>
  <a href="${pageContext.request.contextPath}/cars/1" class="check-all">Show all cars</a>
</p>
  </body>
</html>