<%--
  Created by IntelliJ IDEA.
  User: gil
  Date: 23/5/22
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    String strError = (String)request.getAttribute("error");
    if (strError == null) strError = "";
%>
<body>
    <h1>Bienvenido</h1>

    <h2>Iniciar sesión</h2>
    <p style="color:red;"><%= strError %></p>
    <form method="POST" action="/iniciarSesion">
        <label>Email</label>
        <input type="email" name="email" required/>
        <label>Contraseña</label>
        <input type="password" name="pass" required/>
        <input type="submit" value="Iniciar sesión"/>
    </form>

    <h2>¿No estás registrado? ¡Hazlo ahora!</h2>
</body>
</html>