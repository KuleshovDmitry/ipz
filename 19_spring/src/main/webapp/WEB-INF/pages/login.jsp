<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>19_Spring</title>
</head>

<body>
<form action="/j_spring_security_check" method="POST">
    Login: <input type="text" name="j_username"/>
    Password: <input type="password" name="j_password"/>
    <input type="submit" value="Sing in">
</form>

</body>
</html>
