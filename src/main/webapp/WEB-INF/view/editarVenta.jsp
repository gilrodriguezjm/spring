<%@ page import="es.taw.springsalidos.dto.ProductoDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 31/05/2022
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Venta</title>
</head>
<%
    ProductoDTO producto_a_editar = (ProductoDTO) request.getAttribute("producto");
%>
<body>

<h2>Introducir nueva información sobre el producto : </h2>

<form:form method="post" action="/ventas/confirmarActualizarVenta" modelAttribute="producto">
    <form:hidden path="id" />
    <form:hidden path="precio_compra" />

    <form:input path="nombre" required="required" />
    <label>Nombre producto : </label>
    <form:input path="fecha_venta" required="required" />
    <label>Fecha de puesta en venta : </label>
    <form:input path="precio_salida" />
    <label>Precio salida de producto : </label>
    <p>Introducir estado del producto : </p>
    <form:radiobutton path="estadoByEstadoId" value="0" />
    <label>Perfecto</label>
    <form:radiobutton path="estdoByEstadoId" value="1" />
    <label>Bueno</label>
    <form:radiobutton path="estdoByEstadoId" value="2" />
    <label>Regular</label>
    <form:radiobutton path="estdoByEstadoId" value="3" />
    <label>Mal</label>
    <form:radiobutton path="estdoByEstadoId" value="4" />
    <label>Fatal</label>
    <p>Introducir intereses del producto : </p>
    <form:checkbox path="productoInteresById" value="0" />
    <label>Deporte</label>
    <form:checkbox path="productoInteresById" value="1" />
    <label>Música</label>
    <form:checkbox path="productoInteresById" value="2" />
    <label>Coches</label>
    <form:checkbox path="productoInteresById" value="3" />
    <label>Tecnología</label>
    <form:checkbox path="productoInteresById" value="4" />
    <label>Ropa</label>
    <form:button>Editar venta</form:button>


</form:form>



</body>
</html>
