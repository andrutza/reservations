<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add room</title>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">--%>
</head>
<body>

<table class="rooms">
    <tr>
        <th>Room id</th>
        <th>Room number</th>
        <th>Room max capacity</th>
        <th>Reservation id</th>
    </tr>
    <c:forEach items="${rooms}" var="room" >
        <tr id="room<c:out value="room.id"/>">
            <td class="">
                <c:out value="${room.id}" />
            </td>
            <td class="">
                <c:out value="${room.number}" />
            </td>
            <td class=""><c:out value="${room.maxCapacity}" /></td>
            <td class=""><c:out value="${room.reservationId}" /></td>
        </tr>
    </c:forEach>
</table>

<h2>Add room:</h2>
<sf:form method="POST"  commandName="room">
    <sf:label path="id" cssErrorClass="error">Id</sf:label>:
    <sf:input path="id" cssErrorClass="error"/> <br><br>
    <sf:label path="number" cssErrorClass="error">Number</sf:label>:
    <sf:input path="number" cssErrorClass="error"/> <br><br>
    <sf:label path="maxCapacity" cssErrorClass="error">Max capacity</sf:label>:
    <sf:input path="maxCapacity" cssErrorClass="error"/>  <br><br>
    <input id="addButton" type="submit" value="Add room"/>
</sf:form>

<%--<h2><a href="<c:url value="/delete"/>">Add reservation</a></h2>--%>

<%--<h2>User 2:</h2>--%>
<%--<h2><a href="<c:url value="/delete"/>">Add reservation</a></h2>--%>
<%--<h2><a href="<c:url value="/add"/>">Add reservation</a></h2>--%>

</body>
</html>