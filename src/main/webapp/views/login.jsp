<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <form action="/login" method="post"> <%--action="login.do" for JEE--%>
        Enter your name:
        <label>
            <input type="text" name="name"/>
        </label>
        Password:
        <label>
            <input type="password" name="password"/>
        </label>
        <input type="submit" value="login"/>
        <p><span style="color: red; "> ${errorMessage}</span></p>
    </form>
</div>

<%@ include file="common/footer.jspf" %>