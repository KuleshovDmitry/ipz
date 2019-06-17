<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.nixsolutions.ppp.model.entity.Book" %>
<%@ page import="com.nixsolutions.ppp.model.entity.Author" %>
<%@ page import="com.nixsolutions.ppp.model.entity.Genre" %>
<%@ page import="com.nixsolutions.ppp.model.entity.RentedBook" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<form action="/logout" method="GET">
    <input type="submit" value="Logout">
</form>
<br>
<table id="rentebBookList" border="3">

    <c:forEach var="rentebBook" items="${rentedBookList}">
        <tr>
            <td>${rentebBook.getBook().getName()}</td>
            <td>${rentebBook.getBook().getYear()}</td>
            <td>${rentebBook.getBook().getGenre().getName()}</td>
            <td>${rentebBook.getBook().getAuthor().getFirstName()}</td>
            <td>${rentebBook.getBook().getAuthor().getLastName()}</td>
            <td>${rentebBook.getDateTaken()}</td>
            <td>${rentebBook.getMustBeReturnedDate()}</td>
            <td>${rentebBook.isReturned()}</td>
        </tr>
    </c:forEach>
</table>



</body>

</html>