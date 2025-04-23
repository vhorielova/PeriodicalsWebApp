<%--
  Created by IntelliJ IDEA.
  User: vhorielova
  Date: 20.04.2025
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Вхід</h2>
<form method="post" action="/login">
    <label>Email: <input type="email" name="email"/></label><br/>
    <br>
    <label>Пароль: <input type="password" name="password"/></label><br/>
    <button type="submit">Увійти</button>
    <br>
    <a href="/register"> Create account</a>
</form>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
</body>
</html>
