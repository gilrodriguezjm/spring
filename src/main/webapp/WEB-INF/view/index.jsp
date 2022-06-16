<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.springsalidos.dto.PersonaDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: gil & cristian
  Date: 23/5/22
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bienvenido</title>
</head>
<%
    String strError = (String)request.getAttribute("error");
    if (strError == null) strError = "";

    String strError_res = (String)request.getAttribute("error");
    if (strError_res == null) strError_res = "";

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
    <p style="color:red;"><%= strError_res %></p>


    <form method="POST" action="/registro">
        <label>Email</label><br>
        <input type="email" name="email_register" required/><br>
        <label>Contraseña</label><br>
        <input type="password" name="pass_register" required/>  <br>
        <label>Nombre</label><br>
        <input type="text" name="nombre" required/> <br>
        <label>Apellidos</label><br>
        <input type="text" name="apellidos" required/> <br>
        <label>Fecha de nacimiento</label><br>
        <input type="date" name="f_nacimiento" required/><br>
        <label>Domicilio</label><br>
        <input type="text" name="domicilio" required/> <br>
        <label>Ciudad</label><br>
        <input type="text" name="ciudad" required/><br>
        <br>
        <input type="radio" name="sexo"  value='H'>
                   <label>Hombre</label><br>
        <input type="radio" name="sexo"  value='M'>
                  <label>Mujer</label><br>
        <br>
        <input type="hidden" name="rol"  value="Usuario">

        <br>
        <input type="submit" value="Registrate"/>
    </form>



</body>
</html>