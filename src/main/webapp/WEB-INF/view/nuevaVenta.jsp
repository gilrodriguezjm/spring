<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.springsalidos.entity.PersonaEntity" %>
<%@ page import="es.taw.springsalidos.dto.PersonaDTO" %>
<%@ page import="es.taw.springsalidos.dto.ProductoDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 1/6/2022
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nueva venta</title>
</head>


<body>
<h1>Introducir datos del producto a crear : </h1>

<form:form method="get" action="/ventas/crearVenta" modelAttribute="producto">

    <label for="nombre" >Nombre producto : </label>
    <form:input path="nombre" required="required" /><br>
    <label for="precioSalida">Precio salida de producto : </label>
    <form:input path="precioSalida" /><br>
    Introducir estado del producto :
    <form:select path="estadoByEstadoId">
        <form:options items="${estados}" itemLabel="nombre" itemValue="id" />
    </form:select><br>
    Introducir intereses del producto :
    <form:checkboxes path="productoInteresByProductoId" items="${intereses}" itemLabel="nombre" itemValue="id" /><br>

    <form:button>Crear producto</form:button>

</form:form>

</body>
</html>
