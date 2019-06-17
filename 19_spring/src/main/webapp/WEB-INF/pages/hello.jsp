<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>

</head>

<body>
Hi!

<form action="/reservation" method="GET">
    <input type="submit" value="reservation">
    <input type="hidden" name="authorFirstName" value=""/>
    <input type="hidden" name="authorLastName" value=""/>
    <input type="hidden" name="bookName" value=""/></form>

<form action="/userProfile" method="GET">
    <input type="hidden" name="usrId" value="0"/>

    <input type="submit" value="userProfile">
</form>
<sec:authorize access="hasRole('ADMIN')">
<form action="/admin/changeUsers" method="GET">
    <input type="submit" value="changeUsers">
</form>
<form action="/admin/selectAuthor" method="get">
    <input type="submit" value="addBook">
    <input type="hidden" name="authorFirstName" value=""/>
    <input type="hidden" name="authorLastName" value=""/>
</form>
    <form action="/admin/acceptReservation" method="get">
        <input type="submit" value="acceptReservation">
    </form>
</sec:authorize>
</body>
</html>
