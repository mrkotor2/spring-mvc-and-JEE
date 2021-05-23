<%--
  Created by IntelliJ IDEA.
  User: mrkot
  Date: 23.05.2021
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Todo</title>
</head>
<body>
<form action="/add-todo" method="POST">
    Description :
    <label>
        <input name="description" type="text"/>
    </label>
    <input type="submit" value="add"/>
</form>
</body>
</html>
