<%@ page import="es.taw.springsalidos.entity.PersonaEntity" %><%--
  Created by IntelliJ IDEA.
  User: gil
  Date: 28/5/22
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Analista</title>
</head>
<%
    PersonaEntity persona = (PersonaEntity)session.getAttribute("persona");
%>
<body>
    <h1>Bienvenido analista</h1>
    <a href="#">Cerrar sesión</a>
    <h2>Datos personales</h2>
    <p>
        Nombre: <%= persona.getNombre() %></br>
        Apellidos: <%= persona.getApellidos() %></br>
        Email: <%= persona.getEmail() %>
    </p>
    <a href="#">Generar informe</a>
    <h2>Informes generados</h2>
</body>
</html>
