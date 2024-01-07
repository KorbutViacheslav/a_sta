<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <% request.setAttribute("pageTitle", "Error Page"); %>
    <%@ include file="head.jsp" %>
</head>
<body>
<%@ include file="navigation.jsp" %>

<h2> <%= request.getAttribute("message")%> </h2>
<h3>url: <%= request.getAttribute("url")%> </h3>
</body>
</html>