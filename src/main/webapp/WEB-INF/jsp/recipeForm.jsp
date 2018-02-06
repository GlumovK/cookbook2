<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <section>
            <jsp:useBean id="recipe" type="ru.dartIt.model.Recipe" scope="request"/>
            <h3>Добавить рецепт</h3>
            <hr>
            <form method="post" action="recipies">
                <input type="hidden" name="id" value="${recipe.id}">
                <div class="form-group">
                    <label for="name">Название</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter name" value="${recipe.name}"
                           name="name" required>
                </div>
                <div class="form-group">
                    <label for="description">Описание</label>
                    <input type="text" class="form-control" id="description" placeholder="Enter description"
                           value="${recipe.description}" name="description" required>
                </div>
                <div class="form-group">
                    <label for="cookAlgorithm">Алгоритм приготовления</label>
                    <input type="text" class="form-control" id="cookAlgorithm" placeholder="Enter cookAlgorithm"
                           value="${recipe.cookAlgorithm}" name="cookAlgorithm" required>
                </div>
                <button type="submit" class="btn btn-default">Сохранить</button>
            </form>
        </section>
    </div>
</div>
</body>
</html>
