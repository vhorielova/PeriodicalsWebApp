<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Корзина</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Кошик</a>
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
    <h2 class="text-center">Кошик</h2>
    <div class="col">
        <div class="col-md-12">
            <div class="card mb-4">
                <c:choose>
                    <c:when test="${empty periodicalsCart}">
                        Кошик пустий
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="item" items="${periodicalsCart}">
                            <div class="card-body">
                                <h5 class="card-title">${item.key.name}</h5>
                                <c:choose>
                                    <c:when test="${item.value == 6}">
                                        <p class="card-text">Ціна на 6 місяців: ${item.key.halfYearPrice}</p>
                                        <c:set var="totalPrice" value="${totalPrice + item.key.halfYearPrice}" />
                                    </c:when>
                                    <c:otherwise>
                                        <p class="card-text">Ціна на 12 місяців: ${item.key.fullYearPrice}</p>
                                        <c:set var="totalPrice" value="${totalPrice + item.key.fullYearPrice}" />
                                    </c:otherwise>
                                </c:choose>
                                <form method="post" action="/cart">
                                    <input type="hidden" name="periodicalId" value="${item.key.id}" />
                                    <input type="hidden" name="delete" value="true" />
                                    <button type="submit" class="btn btn-primary">Видалити з кошика</button>
                                </form>
                            </div>
                        </c:forEach>
                        <div class="card-footer text-right">
                            <strong>Загальна сума: ${totalPrice}</strong>
                        </div>
                        <div class="card-body">
                            <form method="post" action="/cart">
                                <input type="hidden" name="order" value="true" />
                                <button type="submit" class="btn btn-primary">Замовити</button>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
</html>
