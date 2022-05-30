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

        <p>Sobre:</p>
        <%
            if (analisis.getColumna == 0) {
        %>
            <form:radiobutton path="columna" value="0"/>
            <label>Productos vendidos</label>
            <form:radiobutton path="calumna" value="1"/>
            <label>Productos comprados</label>
        <%
            } else {
        %>
            <form:radiobutton path="columna" value="2"/>
            <label>Productos vendidos</label>
            <form:radiobutton path="columna" value="3"/>

            <form:radiobutton path="columna" value="4"/>
        <%
            }
        %>
        <form:button>Editar</form:button>
    </form:form>
</body>
</html>
