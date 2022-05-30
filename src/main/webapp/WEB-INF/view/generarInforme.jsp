<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.springsalidos.entity.PersonaEntity" %>
<%@ page import="es.taw.springsalidos.dto.PersonaDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: gil
  Date: 28/5/22
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Generar informe</title>
</head>
<%
    PersonaDTO persona = (PersonaDTO)session.getAttribute("persona");

    String tabla = (String)request.getAttribute("tabla");
    String informe = (String)request.getAttribute("informe");
%>
<body>
    <h1>Generar informe</h1>
    <%
        if (tabla == null) {
    %>
        <form method="POST" action="/analista/generarInforme">
            <input type="radio" name="tabla" value="0" required>
            <label>Personas</label>
            <input type="radio" name="tabla" value="1">
            <label>Productos</label>
            <input type="submit" value="Siguiente">
        </form>
    <%
        } else {
    %>
        <h2><%= informe %></h2>
        <form method="POST" action="/analista/guardarInforme">
            <input type="hidden" name="tabla" value="<%= tabla %>">
            <p>Sobre:</p></br>
            <%
                if (tabla.equals("0")) {
            %>
                <input type="radio" name="columna" value="0" required>
                <label>Productos vendidos</label>
                <input type="radio" name="columna" value="1">
                <label>Productos comprados</label>
            <%
                } else {
            %>
                <input type="radio" name="columna" value="2" required>
                <label>Precio salida</label>
                <input type="radio" name="columna" value="3">
                <label>Precio compra</label>
                <input type="radio" name="columna" value="4">
                <label>Estado producto</label>
            <%
                }
            %>
            <p>En Orden:</p></br>
            <input type="radio" name="orden" value="0" required>
            <label>Ascendente</label>
            <input type="radio" name="orden" value="1">
            <label>Descendente</label>
            <br>
            <p>Desde:</p></br>
            <input type="date" name="fIni" reaquired>
            <p>Hasta:</p></br>
            <input type="date" name="fFin" reaquired>
            <br><br><br>
            <input type="submit" value="Generar informe">
        </form>
    <%
        }
    %>

</body>
</html>
