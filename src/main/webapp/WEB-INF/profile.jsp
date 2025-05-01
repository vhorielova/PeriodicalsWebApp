<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профіль</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Профіль</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/cart">Кошик</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/catalog">Каталог</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Профіль</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="text-center">Дані користувача</h2>
    <div class="card mx-auto" style="max-width: 600px;">
        <div class="card-body">
            <p class="card-text"><strong>Емейл:</strong> ${user.email}</p>
            <form action="/profile" method="post">
                <label>Ім'я: </label> <input type="text" id="name" name="name" value="${user.name}"> <br>
                <label>Прізвище: </label> <input type="text" id="lastname" name="lastname" value="${user.lastname}"> <br>
                <label>Адреса: </label> <input type="text" id="address" name="address" value="${user.address}"> <br>
                <label>Індекс: </label> <input type="text" id="index" name="index" value="${user.index}"> <br>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                            ${error}
                    </div>
                </c:if>
                <c:if test="${updated}">
                    <div class="alert alert-success" role="alert">
                        <p>Дані успішно оновлено</p>
                    </div>
                </c:if>
                <button type="submit" class="btn btn-primary btn-block">Зберегти дані</button>
            </form>

        </div>
    </div>

    <div class="text-center mt-4">
        <a href="/logout" class="btn btn-primary btn-lg">Вийти</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>