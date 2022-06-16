<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.springsalidos.dto.ProductoDTO" %>
<%@ page import="es.taw.springsalidos.dto.TransaccionDTO" %>
<%@ page import="java.util.List" %>
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
    <title>Editar Producto</title>
</head>

<%
    ProductoDTO producto_editar = (ProductoDTO)request.getAttribute("producto_editar");
    String message = (String)request.getAttribute("Control");


%>
<body>
<h2>Datos Producto a editar</h2>
<% if (message!=null){

    %>

<p style="color:green;"><%= message %></p>
<%
    }
%>


<a href="/administrador/">Inicio</a>
<br><br>
<table border="1">
    <thead>
    <tr>
        <th>
            Id Producto: <%= producto_editar.getId()%><br>
            Fecha Venta: <%= producto_editar.getFechaVenta()%><br>
        </th>

    </tr>
    </thead>

</table>

<br><br>




<form:form method="POST" action="/administrador/actualizarProducto/" modelAttribute="producto_editar">
    <form:hidden path="id" />
    <form:hidden path="precioCompra" />
    <form:hidden path="fechaVenta" />

    <label>Nombre producto : </label><br>
    <form:input path="nombre"/>
    <br><br>

    <label>Precio salida de producto : </label><br>
    <form:input path="precioSalida" />
    <br>

    <p>Introducir estado del producto : </p>
    <form:radiobutton path="estadoByEstadoId" value="0" />
    <label>Perfecto</label>
    <form:radiobutton path="estadoByEstadoId" value="1" />
    <label>Bueno</label>
    <form:radiobutton path="estadoByEstadoId" value="2" />
    <label>Regular</label>
    <form:radiobutton path="estadoByEstadoId" value="3" />
    <label>Mal</label>
    <form:radiobutton path="estadoByEstadoId" value="4" />
    <label>Fatal</label>

    <p>Introducir intereses del producto : </p>
    <form:checkbox path="productoInteresByProductoId" value="0" />
    <label>Deporte</label>
    <form:checkbox path="productoInteresByProductoId" value="1" />
    <label>Música</label>
    <form:checkbox path="productoInteresByProductoId" value="2" />
    <label>Coches</label>
    <form:checkbox path="productoInteresByProductoId" value="3" />
    <label>Tecnología</label>
    <form:checkbox path="productoInteresByProductoId" value="4" />
    <label>Ropa</label>
    <br><br>

    <form:button>Editar Producto</form:button>


</form:form>




</body>
</html>
