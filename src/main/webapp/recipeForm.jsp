<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Recipe</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="home.jsp">Home</a></h3>
    <h2>Create recipe</h2>
    <hr>
    <jsp:useBean id="recipe" type="model.Recipe" scope="request"/>
    <form method="post" action="recipies">
        <input type="hidden" name="id" value="${recipe.id}">
        <dl>
            <dt>name:</dt>
            <dd><input type="text" value="${recipe.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>description:</dt>
            <dd><input type="text" value="${recipe.description}"  name="description" required></dd>
        </dl>
        <dl>
            <dt>cookAlgorithm:</dt>
            <dd><input type="text" value="${recipe.cookAlgorithm}" name="cookAlgorithm" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
