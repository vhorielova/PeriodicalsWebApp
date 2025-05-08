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
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'admin'}">
                        <div class="card-body">
                            <form method="post" action="/catalog">
                                <input type="hidden" name="addPeriodical" value="${true}" />
                                <label>Назва: </label> <input type="text" name="name" id="name"/><br>
                                <label>Ціна за 6 місяців: </label> <input type="text" name="halfYearPrice" id="halfYearPrice"/><br>
                                <label>Ціна за 12 місяців: </label> <input type="text" name="fullYearPrice" id="fullYearPrice"/><br>
                                <c:if test="${not empty error}">
                                    <div class="alert alert-danger" role="alert">
                                            ${error}
                                    </div>
                                </c:if>
                                <button type="submit" class="btn btn-primary">Додати періодичне видання</button>
                            </form>
                            <br>
                        </div>
                    </c:when>
                </c:choose>
                <c:forEach var="item" items="${periodicals}">
                    <div class="card-body">
                        <h5 class="card-title">${item.name}</h5>
                        <p class="card-text">Ціна за 6 місяців: ${item.halfYearPrice}</p>
                        <p class="card-text">Ціна за 12 місяців: ${item.fullYearPrice}</p>
                        <c:set var="alreadyInCart" value="false" />
                        <c:set var="ordered" value="false" />
                        <c:forEach var="cartItem" items="${periodicalsCart}">
                            <c:if test="${cartItem.key.id == item.id}">
                                <c:set var="alreadyInCart" value="true" />
                            </c:if>
                        </c:forEach>

                        <c:forEach var="subscriptionItem" items="${subscriptions}">
                            <c:if test="${subscriptionItem.periodicalId == item.id && !subscriptionItem.expired}">
                                <c:set var="ordered" value="true" />
                            </c:if>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${alreadyInCart}">
                                <button class="btn btn-secondary" disabled>У кошику</button>
                            </c:when>
                            <c:when test="${ordered}">
                                <button class="btn btn-success" disabled>Оформлено</button>
                            </c:when>
                            <c:otherwise>
                                <form method="post" action="/catalog">
                                    <input type="hidden" name="periodicalId" value="${item.id}" />
                                    <div class="form-group">
                                        <label for="subscription_${item.id}">Період підписки:</label>
                                        <select class="form-control" name="subscriptionPeriod" id="subscription_period" required>
                                            <option value="6">6 місяців</option>
                                            <option value="12">12 місяців</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Додати до кошика</button>
                                </form>
                                <br>

                                <c:choose>
                                    <c:when test="${sessionScope.user.role == 'admin'}">
                                        <form method="post" action="/catalog">
                                            <input type="hidden" name="deletePeriodical" value="${item.id}" />
                                            <button type="submit" class="btn btn-danger">Видалити періодичне видання</button>
                                        </form>
                                        <br>
                                    </c:when>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
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