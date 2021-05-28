<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<H1>Your Todo List</H1>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Description</th>
            <th>Date</th>
            <th>Completed</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todoList}" var="todo">
            <tr>
                <td>${todo.description}</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy"
                                    value="${todo.targetDate}"/></td>
                <td>${todo.done}</td>
                <td><a type="button" class="btn btn-warning"
                       href="/update-todo?id=${todo.id}">Edit</a></td>
                <td><a type="button" class="btn btn-danger"
                       href="/delete-todo?id=${todo.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a type="button" class="btn btn-success" href="/add-todo">Add</a>
    </div>
</div>

<%@ include file="common/footer.jspf" %>