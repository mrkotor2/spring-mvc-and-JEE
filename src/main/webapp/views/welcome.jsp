<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
<%--    Welcome, ${name}! <a href="/list-todo">Click here</a> to start maintaining your todo's.--%>
    <spring:message code="welcome.message"/> ${name}.
</div>

<%@ include file="common/footer.jspf" %>