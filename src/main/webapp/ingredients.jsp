<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><a href="recipies">Home</a></h3>
<form method="post" action="ingredients?action=create">
    <dl>
        <dt>Добавить новый ингредиент</dt>
        <dd><input type="text" name="ingredientName" value="${param.ingredientName}"></dd>
    </dl>
    <button type="submit">Добавить</button>
</form>

<form method="post" action="ingredients?action=addIngredientToRecipe">
    <dl>
        <dt>Добавить ингредиент(id) в рецепт(id)</dt>
        <dd><input type="text" name="ingredientId" value="${param.ingredientId}"></dd>
        <dd><input type="text" name="recipeId" value="${param.recipeId}"></dd>
    </dl>
    <button type="submit">Добавить</button>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>
