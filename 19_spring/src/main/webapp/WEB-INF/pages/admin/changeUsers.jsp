<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN</title>
</head>
<body>
<form action="/admin/changeUsers" method="POST">
    Login: <input type="text" name="login"/>
    Password: <input type="password" name="password"/>
    <select name="role">
        <option value="ADMIN">ADMIN</option>
        <option value="USER">USER</option>
    </select>
    <select name="action">
        <option value="create">create</option>
        <option value="change">change</option>
        <option value="delete">delete</option>
    </select>
    <input type="submit" value="Change">
</form>
<form action="/logout" method="GET">
    <input type="submit" value="Logout">
</form>
<br>
<table border="3">

    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.getLogin()}</td>
            <td>${user.getPassword()}</td>
            <td>${user.getRole().getName()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>