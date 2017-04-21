<%-- 
    Document   : login
    Created on : 28.02.2017, 16:10:05
    Author     : Алена
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"   
      xmlns:p="http://primefaces.org/ui"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Авторизация</title>
    </head>
    <body style="text-align: center; font-size: 18px; position: absolute;
          top: 30%;
left: 30%;
 height: 30%;
width: 30%;">
     
       <form action="j_security_check" method="POST">
          Авторизация
          <br/>
           Логин:<input type="email" name="j_username">
           <br/><br/>
           Пароль:<input type="password" name="j_password">
           <br/><br/>
           <input type="submit" value="Вход">  
           <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
       </form>
        
   
</body>
</html>


















