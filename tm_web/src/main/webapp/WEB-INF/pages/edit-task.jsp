<%@ page import="com.taskmanager.model.Priority" %>
<%@ page import="com.taskmanager.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%@ include file="head.jsp" %>
    <% request.setAttribute("pageTitle", "Edit existing Task"); %>
</head>
<body>
<%@ include file="navigation.jsp" %>
<h1>${request.getAttribute("pageTitle")}</h1>

<%
    Task task = (Task) request.getAttribute("task");
%>

<form method="post" action="/edit-task">

    <label for="id">ID:</label>
    <input type="text" id="id" name="id" value="<%= task.getId() %>" readonly><br><br>

    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="<%= task.getTitle() %>" required><br><br>

    <label for="priority">Priority:</label>
    <select id="priority" name="priority">
        <% for (Priority priority : Priority.values()) { %>
        <option value="<%= priority.name() %>" <%= task.getPriority() == priority ? "selected" : "" %>>
            <%= priority.name() %></option>
        <% } %>
    </select><br><br>

    <input type="submit" value="Update Task">
</form>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<p style="color: red;"><%= errorMessage %></p>
<%
    }
%>

</body>
</html>