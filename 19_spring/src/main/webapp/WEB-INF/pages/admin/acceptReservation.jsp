<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Reservation" %>
<%@ page import="entity.Book" %>
<%@ page import="entity.Author" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
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
                form.elements.reservationId.value = row.cells[0].innerHTML;
                if (confirm("accept?"))form.submit();
            }
        };
    }
</script>
<form action="/acceptReservation" method="POST">
    <input type="text" name="name"/>
    <input type="text" name="isbn"/>
    <input type="text" name="year"/><!-- MB EXEPTION-->
    <input type="hidden" name="authorId" value="${authorId}"/>
    <input type="submit" value="Create">
</form>
<br>
<table id="reservations" border="3">

    <c:forEach var="reservation" items="${reservationList}">
        <tr>
            <td>${reservation.getId()}</td>
            <td>${reservation.getDate   ()}</td>
            <td>${reservation.getBook().getName()}</td>
            <td>${reservation.getBook().getAuthor().getLastName()}</td>
            <td>${reservation.getUser().getLogin()}</td>
        </tr>
    </c:forEach>
</table>

<form id="acceptReservationForm" action="/admin/acceptReservation" method="post">
    <input type="hidden" name="reservationId">
    <script type="text/javascript">
        select("reservations", "clicked_Row", document.getElementById("acceptReservationForm"));
    </script>
</form>
<form action="/logout" method="GET">
    <input type="submit" value="Logout">
</form>

</body>
</html>