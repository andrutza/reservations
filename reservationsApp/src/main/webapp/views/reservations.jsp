<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reservations</title>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">--%>
</head>
<body>
<h1>Reservations:</h1>
<table class="reservations">
    <tr>
        <th>Reservation id</th>
        <th>Reservation name</th>
        <th>Reservation date</th>
    </tr>
    <c:forEach items="${reservations}" var="reservation" >
        <tr id="reservation_<c:out value="reservation.id"/>">
            <td class="">
                <c:out value="${reservation.id}" />
            </td>
            <td class="">
                <c:out value="${reservation.name}" />
            </td>
            <td class=""><c:out value="${reservation.date}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>