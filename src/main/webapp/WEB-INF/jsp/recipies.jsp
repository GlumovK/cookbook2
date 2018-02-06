<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/recipies.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">

        <h3>Recipies</h3>
        <hr>
        <form method="post"  action="recipies/getByName">
            <div class="form-group">
                <label for="name">Искать рецепт по названию</label>
                <input type="text" class="form-control" id="name" value="${param.name}" placeholder="Enter name"
                       name="name">
            </div>
            <button type="submit" class="btn btn-default" onclick="update()">Искать</button>
        </form>
        <hr>
        <form method="post" id="userName" action="recipies/getByUser">
            <div class="form-group">
                <label for="user">Искать рецепт по автору</label>
                <input type="text" class="form-control" id="user" value="${param.user}" placeholder="Enter user"
                       name="user">
            </div>
            <button type="submit" class="btn btn-default">Искать</button>
        </form>
        <hr>
        <form method="post" action="recipies/getByIngredient">
            <div class="form-group">
                <label for="ingredient">Искать рецепт по ингредиентам</label>
                <input type="text" class="form-control" id="ingredient" value="${param.ingredient}"
                       placeholder="Enter ingredient" name="ingredient">
            </div>
            <button type="submit" class="btn btn-default">Искать</button>
        </form>
        <hr>
        <form method="post" action="recipies/getByCatalog">
            <div class="form-group">
                <label for="catalog">Искать рецепт по каталогу (Soup, Second course, Snack, Salad, Drink)</label>
                <input type="text" class="form-control" id="catalog" value="${param.catalog}"
                       placeholder="Enter catalog" name="catalog">
            </div>
            <button type="submit" class="btn btn-default">Искать</button>
        </form>
        <hr>
        <a href="recipies/create">Добавить рецепт</a>
        <hr>
        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th>name</th>
                <th>description</th>
                <th>cookAlgorithm</th>
                <th>ingredients</th>
                <th>user.name</th>
                <th>rating</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${recipies}" var="recipe">
                <jsp:useBean id="recipe" scope="page" type="ru.dartIt.model.Recipe"/>
                <tr>
                    <td>${recipe.name}</td>
                    <td>${recipe.description}</td>
                    <td>${recipe.cookAlgorithm}</td>
                    <td>
                        <c:forEach items="${recipe.ingredients}" var="ingredient">
                            ${ingredient.name}
                        </c:forEach>
                    </td>
                    <td>${recipe.user.name}</td>
                    <td>${recipe.rating}</td>
                    <td><a href="recipies/addVote?id=${recipe.id}">+1</a></td>
                    <td><a href="recipies/subtractVote?id=${recipe.id}">-1</a></td>
                </tr>
            </c:forEach>
        </table>
        !
        <div id="ajaxGetUserServletResponse"></div>
    </div>
</div>
</body>
</html>
