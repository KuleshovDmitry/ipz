<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<form action="/admin/createBook" method="POST">
    <input type="text" name="name"/>
    <input type="text" name="isbn"/>
    <input type="text" name="year"/><!-- MB EXEPTION-->
    <input type="hidden" name="authorId" value="${authorId}"/>
    <input type="submit" value="Create">
</form>
<form action="/logout" method="GET">
    <input type="submit" value="Logout">
</form>
</body>
</html>