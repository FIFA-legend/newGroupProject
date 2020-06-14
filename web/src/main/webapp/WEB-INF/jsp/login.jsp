<%--
  Created by IntelliJ IDEA.
  User: kolod
  Date: 14.06.2020
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/myLogin" method="post">
        <label for="name">Username:</label>
        <input type="text" name="username" id="name"> <br>
        <label for="pass">Password:</label>
        <input type="text" name="password" id="pass"> <br>
        <button type="submit">Login</button>
    </form>
</body>
</html>
