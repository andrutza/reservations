<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add reservation</title>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">--%>
</head>
<body>
<h2>Add reservation:</h2>
<sf:form method="POST"  commandName="reservation">
    <sf:label path="id" cssErrorClass="error">Id</sf:label>:
    <sf:input path="id" cssErrorClass="error"/> <br><br>
    <sf:label path="name" cssErrorClass="error">Name</sf:label>:
    <sf:input path="name" cssErrorClass="error"/> <br><br>
    <sf:label path="roomNo" cssErrorClass="error">Number of room</sf:label>:
    <sf:input path="roomNo" cssErrorClass="error"/>  <br><br>
    <input id="addButton" type="submit" value="Add reservation"/>
</sf:form>

<%--<h2><a href="<c:url value="/delete"/>">Add reservation</a></h2>--%>

<%--<h2>User 2:</h2>--%>
<%--<h2><a href="<c:url value="/delete"/>">Add reservation</a></h2>--%>
<%--<h2><a href="<c:url value="/add"/>">Add reservation</a></h2>--%>

</body>
</html>