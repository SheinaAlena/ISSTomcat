<%-- 
    Document   : login
    Created on : 28.02.2017, 16:10:05
    Author     : Алена
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Авторизация</title>
    </head>
    <body>
        <form action="j_security_check" method="POST">
            Логин:<input type="text" name="j_username"><br>
            Пароль:<input type="password" name="j_password">
            <input type="submit" value="Вход">
        </form>

    </body>
</html>
