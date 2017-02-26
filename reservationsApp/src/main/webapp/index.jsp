<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <title>PersonApplication</title>
  <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">--%>
</head>
<body>
  <h1>
    Welcome to this application
  </h1>

  <h2><a href="<c:url value="/reservations"/>">View reservations</a></h2>
  <%--<h2><a href="<c:url value="/add"/>">Add reservations</a></h2><br>--%>
</body>
</html>
