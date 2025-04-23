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
    <title>Registration</title>
</head>
<body>
<h2>Реєстрація</h2>
<form method = "post" action = "/register">
    <label for="name">Ім'я:</label><input type="text" id="name" name="name"/><br><br>
    <label>Прізвище:</label><input type="text" id="lastname" name="lastname"/><br><br>
    <label>Електрона пошта: <input type="email" id="email" name="email"/></label><br><br>
    <label>Адреса доставки: <input type="text" id="address" name="address"/></label><br><br>
    <label>Поштовий індекс: <input type="text" id="index" name="index"/></label><br><br>
    <label>Пароль: <input type="password" id="password" name="password"/></label><br><br>
    <label>Пароль: <input type="password" id="confirmPassword" name="confirmPassword"/></label><br><br>
    <p>${error}</p>
    <button type="submit">Зареєструватися</button>
</form>
</body>
</html>
