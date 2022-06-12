<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.springsalidos.dto.PersonaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: cristian
  Date: 28/5/22
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ver Persona</title>
</head>

<%
    PersonaDTO persona_ver = (PersonaDTO)request.getAttribute("persona_ver");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String fecha_nacimiento = dateFormat.format(persona_ver.getFecha_nacimiento());
    String sexo="";
    if(persona_ver.getSexo()=="H")
    {
        sexo="Hombre";
    }
    else {sexo="Mujer";}
%>
<body>
<h2>Datos Usuario</h2>

<p>
    <a href="/administrador/">Inicio</a>
<br><br>



<table class="default" border="1">

    <tr>

        <td><b>Email</b></td>

        <td><%= persona_ver.getEmail() %></td>


    </tr>

    <tr>

        <td><b>Nombre</b></td>

        <td><%= persona_ver.getNombre() %></td>


    </tr>

    <tr>

        <td><b>Apellidos</b></td>

        <td><%= persona_ver.getApellidos() %></td>


    </tr>
    <tr>

        <td><b>Fecha Nacimiento</b></td>
        <td><%= fecha_nacimiento %></td>


    </tr>
    <tr>

        <td><b>Monedero</b></td>
        <td><%= persona_ver.getMonedero() %></td>


    </tr>
    <tr>

        <td><b>Rol</b></td>
        <td><%=  persona_ver.getRol() %></td>


    </tr>

    <tr>

        <td><b>Domicilio</b></td>
        <td><%=  persona_ver.getDomicilio() %></td>


    </tr>

    <tr>

        <td><b>Ciudad</b></td>
        <td><%=  persona_ver.getCiudad() %></td>


    </tr>

    <tr>

        <td><b>Sexo</b></td>
        <td><%= sexo %></td>


    </tr>
</table>


</p>





</body>
</html>
