<%-- 
    Document   : registration
    Created on : 22.03.2017, 17:58:26
    Author     : Алена
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Регистрация</title>
    </head>
    <body>
        <h1>Регистрация</h1>
        <form method="POST" action="registration">
            <p>
                <label for="surname">Фамилия</label>
                <input type="text" name="surname" id="surname"/>
            </p>
            <p>
                <label for="name">Имя</label>
                <input type="text" name="name" id="name"/>
            </p>
            <p>
                <label for="middlename">Отчество</label>
                <input type="text" name="middlename" id="middlename"/>
            </p>
            <p>
                <label for="organization">Организация</label>
                <input type="text" name="organization" id="organization"/>
            </p>
            <p>
                <label for="phone">Контактный телефон</label>
                <input type="text" name="phone" id="phone"/>
            </p>
            <p>
                <label for="login">Логин</label>
                <input type="email" name="login" id="login"/>
            </p>
            
            <p>
                <label for="password">Пароль</label>
                <input type="password" name="password" id="password"/>

                <label for="password2">Повторите пароль</label>
                <input type="password" name="password2" id="password2"/>
            </p>
            <p>
                <button type="submit">Зарегистрироваться</button>
            </p>
        </form>
    </body>
</html>