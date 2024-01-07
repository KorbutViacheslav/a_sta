<%@ page import="com.taskmanager.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <% request.setAttribute("pageTitle", "Read existing Task"); %>
    <%@ include file="head.jsp" %>
</head>
<body>
<%@ include file="navigation.jsp" %>
<h1>${request.getAttribute("pageTitle")}</h1>

<%
    Task task = (Task) request.getAttribute("task");
%>

<table>
    <%-- TODO: implement table--%>
</table>

</body>
</html>


