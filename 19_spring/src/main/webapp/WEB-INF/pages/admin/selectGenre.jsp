<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Book" %>
<%@ page import="entity.Author" %>
<%@ page import="entity.Genre" %>
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
                form.elements.id.value = row.cells[0].innerHTML;
                form.submit();
            }
        };
    }
</script>
<form action="/admin/selectAuthor" method="post">
    FN: <input type="text" name="authorFirstName"/>
    LN: <input type="text" name="authorLastName"/>
    <input type="submit" value="find">
</form>
<form action="/logout" method="GET">
    <input type="submit" value="Logout">
</form>
<br>
<table id="authors" border="3">

    <c:forEach var="genre" items="${genreList}">
        <tr>
            <td id="id">${genre.getId()}</td>
            <td>${genre.getName()}</td>
            <td>${genre.getDescription()}</td>
        </tr>
    </c:forEach>
</table>

<form id="selectAuthorForm" action="/admin/setBook" method="get">
    <input type="hidden" name="id">
    <input type="hidden" name="genreName" value="">
    <script type="text/javascript">
        select("authors", "clicked_Row", document.getElementById("selectBookForm"));
    </script>
</form>


<form action="/admin/setBook" method="get">
    <input type="text" name="genreName"/>
    <input type="text" name="genreDescription"/>
    <input type="hidden" name="genreId">
    <input type="submit" value="createNewAuthor">
</form>
</body>

</html>