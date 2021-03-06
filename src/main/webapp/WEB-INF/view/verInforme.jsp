<%@ page import="es.taw.springsalidos.dto.TransaccionDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.springsalidos.dto.AnalisisDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.taw.springsalidos.dto.ProductoDTO" %><%--
  Created by IntelliJ IDEA.
  User: gil
  Date: 30/5/22
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Informe</title>
</head>
<%
    AnalisisDTO analisis = (AnalisisDTO)request.getAttribute("analisis");
    List<TransaccionDTO> transacciones = (List)request.getAttribute("transacciones");
    List<ProductoDTO> productos = (List)request.getAttribute("productos");
    String sinDatos = "";
    SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
%>
<body>
<h1>Informe <%= analisis.getId() %></h1>
<a href="/analista/">Inicio</a>
<p>Descripción: <%= analisis.getDescripcion() %></p>
<p>Desde: <%= fecha.format(analisis.getFechaInicio()) %> - Hasta: <%= fecha.format(analisis.getFechaFinal()) %></p>
<%
    if (transacciones != null || productos != null) {
%>
<table border="1">
    <%
        if (analisis.getTabla() == 0) {
            if (transacciones.size() == 0)
                sinDatos = "No hay datos disponibles para este informe.";
    %>
    <thead>
    <tr>
        <%
            if (analisis.getColumna() == 0) {
        %>
        <th style="color: blue;">PRECIO VENTA</th>
        <%
        } else if(analisis.getColumna() == 1) {
        %>
        <th style="color: blue;">PRECIO COMPRA</th>
        <%
        } else {
        %>
        <th style="color: blue;">PRECIO PUJA</th>
        <%
            }
        %>
        <th>EMAIL</th>
        <th>NOMBRE</th>
        <th>PRODUCTO</th>
        <th>FECHA TRANSACCIÓN</th>
    </tr>
    </thead>
    <%
    } else {
        if(productos.size() == 0)
            sinDatos = "No hay datos disponibles para este informe.";
    %>
    <thead>
    <tr>
        <th <% if (analisis.getColumna() == 2) { %> style="color: blue;" <% } %> >PRECIO SALIDA</th>
        <th>NOMBRE</th>
        <th <% if (analisis.getColumna() == 4) { %> style="color: blue;" <% } %> >ESTADO</th>
        <th <% if (analisis.getColumna() == 3) { %> style="color: blue;" <% } %> >PRECIO COMPRA</th>
    </tr>
    </thead>
    <%
        }
    %>
    <tbody>
    <%
        if (analisis.getTabla() == 0){
            for (int i =0; i < transacciones.size(); i++) {
    %>
    <tr>
        <%
            if (transacciones.get(i).getPrecio() == null) {
        %>
        <td>Precio por determinar</td>
        <%
        } else {
        %>
        <td><%= transacciones.get(i).getPrecio() %> €</td>
        <%
            }
        %>
        <td><%= transacciones.get(i).getPersona().getEmail() %></td>
        <td><%= transacciones.get(i).getPersona().getNombre() %> <%= transacciones.get(i).getPersona().getApellidos() %> </td>
        <td><%= transacciones.get(i).getProducto().getNombre() %></td>
        <td><%= fecha.format(transacciones.get(i).getFechaTransaccion()) %></td>
    </tr>
    <%
        }
    } else {
        for (int i = 0; i < productos.size(); i++) {
            String estado = "";
            if(productos.get(i).getEstadoByEstadoId().equals(0)) {
                estado = "Perfecto";
            } else if(productos.get(i).getEstadoByEstadoId().equals(1)){
                estado = "Bueno";
            } else if(productos.get(i).getEstadoByEstadoId().equals(2)){
                estado = "Regular";
            } else if(productos.get(i).getEstadoByEstadoId().equals(3)){
                estado = "Mal";
            } else {
                estado = "Fatal";
            }
    %>
    <tr>
        <td><%= productos.get(i).getPrecioSalida() %> €</td>
        <td><%= productos.get(i).getNombre() %></td>
        <td><%= estado %></td>
        <!--
             Antes tenía esto: productos.get(i).getEstadoByEstadoId().getNombre()
             y funcionaba perfectamente, podía acceder con la relación al nombre
             del estado, pero en el último push que han hecho, han cambiado algo
             y ahora es como si no estuviese relacionado, no puedo acceder al nombre.

             Por tanto, he tenido que hacerlo de un modo bastante cutre para
             poder mostrar los nombres de los estados.

             Me ha comentado que ha cambiado la lista de estado a int, no sé por qué,
             entonces ya no puedo obtener los nombres porque no hay relación

             ¯\_(ツ)_/¯
         -->
        <td>
            <%
                if (productos.get(i).getPrecioCompra() == null) {
            %>
            En puja
            <%
            } else {
            %>
            <%= productos.get(i).getPrecioCompra() %> €
            <%
                }
            %>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<p><%= sinDatos %></p>
<%
} else {
%>
<h3 style="color: red;">ERROR en la Base de Datos. Consulta con un administrador.</h3>
<%
    }
%>
</body>
</html>