<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-brand">Cookbook</div>
        <div class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" role="form" action="spring_security_check" method="post">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control" name="password">
                </div>
                <button type="submit" class="btn btn-success">
                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                </button>
            </form>
        </div>
    </div>
</div>

<div class="jumbotron">
    <div class="container">
        <br/>
        <p>
            <a class="btn btn-lg btn-success" href="register">Register &raquo;</a>
            <button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('user@yandex.ru', 'password')">
                User
            </button>
            <button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('admin@gmail.com', 'admin')">
                Admin
            </button>
        </p>
        <br/>
    </div>
</div>
<script type="text/javascript">
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>
</body>
</html>