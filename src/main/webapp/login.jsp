<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>登录页面</title>
</head>
<body>
<h2>用户登录</h2>
<form action="login" method="post">
    <label for="username">用户名:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">密码:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="登录">
</form>
<c:if test="${error!= null}">
    <div style="color: red;">${"登录失败，请检查用户名或密码。"}</div>
</c:if>
</body>
</html>