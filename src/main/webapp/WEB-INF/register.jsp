<%--
  Created by IntelliJ IDEA.
  User: vhorielova
  Date: 20.04.2025
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Реєстрація</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Реєстрація</h2>
    <form method="post" action="/register" class="mt-4">
        <div class="form-group">
            <label for="name">Ім'я:</label>
            <input type="text" class="form-control" id="name" name="name" value="${name}"/>
        </div>
        <div class="form-group">
            <label for="lastname">Прізвище:</label>
            <input type="text" class="form-control" id="lastname" name="lastname" value="${lastname}"/>
        </div>
        <div class="form-group">
            <label for="email">Електрона пошта:</label>
            <input type="email" class="form-control" id="email" name="email" value="${email}"/>
        </div>
        <div class="form-group">
            <label for="address">Адреса доставки:</label>
            <input type="text" class="form-control" id="address" name="address" value="${address}"/>
        </div>
        <div class="form-group">
            <label for="index">Поштовий індекс:</label>
            <input type="text" class="form-control" id="index" name="index" value="${index}"/>
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password" value="${password}"/>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Підтвердіть пароль:</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" value="${confirmPassword}"/>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>

        <button type="submit" class="btn btn-primary btn-block">Зареєструватися</button>
        <br>
        <a href="login.jsp" class="btn btn-link">Вже маю акаунт</a>
    </form>
</div>

<!-- Bootstrap JS and dependencies (optional) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>