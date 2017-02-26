<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update user</title>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">--%>
</head>
<body>

<table class="users">
    <tr>
        <th>Id</th>
        <th>Username</th>
        <th>Password</th>
        <th>Name</th>
    </tr>
    <c:forEach items="${users}" var="user" >
        <tr id="user<c:out value="user.id"/>">
            <td class="">
                <c:out value="${user.id}" />
            </td>
            <td class="">
                <c:out value="${user.username}" />
            </td>
            <td class="">
                <c:out value="${user.password}" />
            </td>
            <td class="">
                <c:out value="${user.name}" />
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Update user:</h2>
<sf:form method="POST"  commandName="user">
    <sf:label path="id" cssErrorClass="error">Id</sf:label>:
    <sf:input path="id" cssErrorClass="error"/>  <br><br>
    <sf:label path="password" cssErrorClass="error">Password</sf:label>:
    <sf:input path="password" cssErrorClass="error"/>  <br><br>
    <input id="addButton" type="submit" value="Update user"/>
</sf:form>

</body>
</html>