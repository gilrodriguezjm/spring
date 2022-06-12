<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: gil
  Date: 28/5/22
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar informe</title>
</head>
<%
    String informe = (String)request.getAttribute("informe");
%>
<body>
    <h1>Editar informe <%= informe%></h1>
    <form:form method="POST" action="/analista/actualizarInforme" modelAttribute="analisis">
        <form:hidden path="id" />
        <form:hidden path="tabla" />
        <form:hidden path="persona" />

        <p>Sobre:</p>
        <%
            if (informe.equals("Transacciones")) {
        %>
            <form:radiobutton path="columna" value="0"/>
            <label>Productos vendidos</label>
            <form:radiobutton path="columna" value="1"/>
            <label>Productos comprados</label>
            <form:radiobutton path="columna" value="5"/>
            <label>Productos en puja</label>
        <%
            } else {
        %>
            <form:radiobutton path="columna" value="2"/>
            <label>Precio salida</label>
            <form:radiobutton path="columna" value="3"/>
            <label>Precio compra</label>
            <form:radiobutton path="columna" value="4"/>
            <label>Estado producto</label>
        <%
            }
        %>
        <p>Orden:</p>
        <form:radiobutton path="orden" value="0" required="required"/>
        <label>Ascendente</label>
        <form:radiobutton path="orden" value="0"/>
        <label>Descendente</label>

        <br><br>
        <label>Desde:</label>
        <form:input path="fechaInicio" required="required"/>
        <br><br>
        <label>Hasta:</label>
        <form:input path="fechaFinal" required="required"/>

        <br><br>
        <label>Descripción:</label>
        <br>
        <form:textarea path="descripcion" rows="5" col="12" required="required"/>

        <br><br>
        <form:button>Editar informe</form:button>
    </form:form>
</body>
</html>
