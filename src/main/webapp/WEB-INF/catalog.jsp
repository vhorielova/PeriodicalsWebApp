<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Каталог</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Каталог періодичних видань</a>
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
    <h2 class="text-center">Каталог</h2>
    <div class="col">
        <div class="col-md-12">
            <div class="card mb-4">
                <c:forEach var="item" items="${periodicals}">
                    <div class="card-body">
                        <h5 class="card-title">${item.name}</h5>
                        <p class="card-text">Ціна на шість місяців: ${item.halfYearPrice}</p>
                        <p class="card-text">Ціна на 12 місяців: ${item.fullYearPrice}</p>
                        <a href="#" class="btn btn-primary">Додати до кошика</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>