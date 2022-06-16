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
    <form:hidden path="precioCompra" />

    <label>Nombre producto : </label>
    <form:input path="nombre" required="required" /><br>
    <label>Fecha de puesta en venta : </label>
    <form:input path="fechaVenta" required="required" /><br>
    <label>Precio salida de producto : </label>
    <form:input path="precioSalida" /><br>

    <p>Introducir estado del producto : </p>
    <label>Perfecto</label>
    <form:radiobutton path="estadoByEstadoId" value="0" /><br>
    <label>Bueno</label>
    <form:radiobutton path="estadoByEstadoId" value="1" /><br>
    <label>Regular</label>
    <form:radiobutton path="estadoByEstadoId" value="2" /><br>
    <label>Mal</label>
    <form:radiobutton path="estadoByEstadoId" value="3" /><br>
    <label>Fatal</label>
    <form:radiobutton path="estadoByEstadoId" value="4" /><br>

    <p>Introducir intereses del producto : </p>

    <form:checkboxes path="productoInteresByProductoId" items="${intereses}" itemLabel="nombre" itemValue="id" /><br>
    <%--
    <label>Deporte</label>
    <form:checkbox path="productoInteresByProductoId" value="0" /><br>
    <label>Música</label>
    <form:checkbox path="productoInteresByProductoId" value="1" /><br>
    <label>Coches</label>
    <form:checkbox path="productoInteresByProductoId" value="2" /><br>
    <label>Tecnología</label>
    <form:checkbox path="productoInteresByProductoId" value="3" /><br>
    <label>Ropa</label>
    <form:checkbox path="productoInteresByProductoId" value="4" /><br>
--%>
    <form:button>Editar venta</form:button>


</form:form>



</body>
</html>
