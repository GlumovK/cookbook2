<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Recipe list</title>

</head>
<body>
<section>
    <h3><a href="home.jsp">Home</a></h3>
    <h2>Recipe</h2>
    <%--<a href="meals?action=create">Add Meal</a>--%>
    <hr/>

    <form method="post" action="recipies?action=getByName">
    <dl>
        <dt>Искать рецепт по названию</dt>
        <dd><input type="text" name="name" value="${param.name}"></dd>
    </dl>
    <button type="submit">Искать</button>
</form>

    <form method="post" action="recipies?action=getByUser">
        <dl>
            <dt>Искать рецепт по автору</dt>
            <dd><input type="text" name="user" value="${param.user}"></dd>
        </dl>
        <button type="submit">Искать</button>
    </form>
    </form>
    <form method="post" action="recipies?action=getByIngredient">
        <dl>
            <dt>Искать рецепт по ингредиентам</dt>
            <dd><input type="text" name="ingredient" value="${param.ingredient}"></dd>
        </dl>
        <button type="submit">Искать</button>
    </form>
    <form method="post" action="recipies?action=getByCatalog">
        <dl>
            <dt>Искать рецепт по каталогу (Soup, Second course, Snack, Salad, Drink)</dt>
            <dd><input type="text" name="catalog" value="${param.catalog}"></dd>
        </dl>
        <button type="submit">Искать</button>
    </form>

    <form method="post" action="recipies?action=addToCatalog">
        <dl>
            <dt>Добавить рецепт(id) в каталог(id)</dt>
            <dd><input type="text" name="catalogId" value="${param.catalogId}"></dd>
            <dd><input type="text" name="recipeId" value="${param.recipeId}"></dd>
        </dl>
        <button type="submit">Добавить</button>
    </form>


    <hr/>
    <a href="ingredients.jsp">Add ingredients</a>
    <hr/>

    <hr/>
    <a href="recipies?action=create">Add recipe</a>
    <hr/>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>name</th>
            <th>description</th>
            <th>cookAlgorithm</th>
            <th>ingredients</th>
            <th>rating</th>
            <th>user</th>
            <th></th>
            <th></th>

        </tr>
        </thead>
        <c:forEach items="${recipies}" var="recipe">
            <jsp:useBean id="recipe" scope="page" type="model.Recipe"/>
            <tr >

                <td>${recipe.name}</td>
                <td>${recipe.description}</td>
                <td>${recipe.cookAlgorithm}</td>
                <td>
                    <c:forEach items="${recipe.ingredients}" var="ingredient">
                        ${ingredient.name}
                    </c:forEach>
                </td>
                <td>${recipe.rating}</td>
                <td>${recipe.user.name}</td>
                <td><a href="recipies?action=addVote&id=${recipe.id}">+1</a></td>
                <td><a href="recipies?action=subtractVote&id=${recipe.id}">-1</a></td>

            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>