<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.nixsolutions.ppp.model.entity.Book" %>
<%@ page import="com.nixsolutions.ppp.model.entity.Author" %>
<%@ page import="com.nixsolutions.ppp.model.entity.Genre" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN</title>
</head>
<script type="text/javascript">
    function select(table_Id, click_Class, form) {
        var table = document.getElementById(table_Id);
        if (typeof multiple == 'undefined') multiple = true;


        if (click_Class) table.onclick = function (e) {
            if (!e) e = window.event;
            var elem = e.target || e.srcElement;
            while (!elem.tagName || !elem.tagName.match(/td|th|table/i))
                elem = elem.parentNode;

            if (elem.parentNode.tagName == 'TR' &&
                elem.parentNode.parentNode.tagName == 'TBODY') {
                var click_Class_Reg = new RegExp("\\b" + click_Class + "\\b");
                var row = elem.parentNode;
                form.elements.bookId.value = row.cells[0].innerHTML;
                form.submit();
            }
        };
    }
</script>
<form action="/reservation" method="get">
    FN: <input type="text" name="authorFirstName"/>
    LN: <input type="text" name="authorLastName"/>
    Book: <input type="text" name="bookName"/>
    <input type="submit" value="find">
</form>
<form action="/logout" method="GET">
    <input type="submit" value="Logout">
</form>
<br>
    <table id="books" border="3">

        <c:forEach var="book" items="${bookList}">
            <tr>
                <td id="id">${book.getId()}</td>
                <td>${book.getName()}</td>
                <td>${book.getYear()}</td>
                <td>${book.getGenre().getName()}</td>
                <td>${book.getAuthor().getFirstName()}</td>
                <td>${book.getAuthor().getLastName()}</td>
            </tr>
        </c:forEach>
    </table>

<form id="selectBookForm" action="/reservation" method="POST">
    <input type="hidden" name="bookId">
    <script type="text/javascript">
        select("books", "clicked_Row", document.getElementById("selectBookForm"));
    </script>
</form>


</body>

</html>