<%@ page import="com.taskmanager.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% request.setAttribute("pageTitle", "List of Tasks"); %>
    <%@ include file="head.jsp" %>

</head>
<body>
<%@ include file="navigation.jsp" %>
<h1>${request.getAttribute("pageTitle")}</h1>


<%
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    if (tasks != null && !tasks.isEmpty()) {
%>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Priority</th>
        <th>Action</th>
    </tr>
    <% for (Task task : tasks) { %>
    <tr>
        <td><%= task.getId() %>
        </td>
        <td><%= task.getTitle() %>
        </td>
        <td><%= task.getPriority() %>
        </td>
        <td>
            <a href="<%= request.getContextPath() %>/read-task?id=<%= task.getId() %>">Read</a> |
            <a href="<%= request.getContextPath() %>/edit-task?id=<%= task.getId() %>">Edit</a> |
            <a href="<%= request.getContextPath() %>/delete-task?id=<%= task.getId() %>">Delete</a>
        </td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p>No tasks available</p>
<% } %>
</body>
</html>
