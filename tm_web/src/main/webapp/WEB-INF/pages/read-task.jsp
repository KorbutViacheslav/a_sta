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
    <tr>
        <td>Id: </td>
        <td><b><%=task.getId()%></b></td>
    </tr>
    <tr>
        <td>Title: </td>
        <td><b><%=task.getTitle()%></b></td>
    </tr>
    <tr>
        <td>Priority: </td>
        <td><b><%=task.getPriority()%></b></td>
    </tr>
</table>

</body>
</html>


