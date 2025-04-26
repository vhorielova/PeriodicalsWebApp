<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Періодичні видання</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Періодичні видання</h1>
    <p class="text-center">Тут ви можете оформити підписку на журнали чи газети</p>
    <div class="text-center mt-4">
        <c:if test="${!loggedIn}">
            <a href="/login" class="btn btn-primary btn-lg">Увійти</a>
            <a href="/register" class="btn btn-secondary btn-lg">Зареєструватись</a>
        </c:if>
        <c:if test="${loggedIn}">
            <a href="/profile" class="btn btn-primary btn-lg">Профіль</a>
            <a href="/catalog" class="btn btn-primary btn-lg">Каталог</a>
            <a href="/cart" class="btn btn-primary btn-lg">Корзина</a>
        </c:if>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>