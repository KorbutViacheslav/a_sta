<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="head.jsp" %>
    <% request.setAttribute("pageTitle", "Create New Task"); %>
    <title></title>
</head>
<body>
<%@ include file="navigation.jsp" %>

<h1>${request.getAttribute("pageTitle")}</h1>
<%
    String error = request.getParameter("error");
    if (error != null && error.equals("taskExists")) {
%>
<p style="color: red;">
    Task with this title already exists. Please enter a different title.
</p>
<%
    }
%>

<form method="post" action="create-task">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br><br>

    <label for="priority">Priority:</label>
    <select id="priority" name="priority">
        <option value="LOW">Low</option>
        <option value="MEDIUM">Medium</option>
        <option value="HIGH">High</option>
    </select><br><br>
    <input type="submit" value="Create Task">
    <input type="reset" value="Reset">
</form>


</body>
</html>