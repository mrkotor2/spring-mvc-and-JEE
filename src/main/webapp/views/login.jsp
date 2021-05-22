<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>LOGIN</title>
</head>

<body>
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
</body>

</html>