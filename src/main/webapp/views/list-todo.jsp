<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo for ${name}</title>
</head>
<body>
<H1>Your Todo List</H1>
<div>
    <table>
        <thead>
        <tr>
            <th>Description</th>
            <th>Date</th>
            <th>Completed</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todoList}" var="todo">
            <tr>
                <td>${todo.description}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.done}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<a class="button" href="/add-todo">Add</a>
</body>
</html>