<%-- 
    Document   : login
    Created on : 25.01.2017, 17:10:30
    Author     : Алена
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authorization</title>
    </head>
    <body>
        <form action="j_security_check" method="POST">
   Username: <input type="text" name="j_username"><br>
   <br>
   <br>
   Password:<input type="password" name="j_password">
   <input type="submit" value="Login">
</form>
    </body>
</html>
