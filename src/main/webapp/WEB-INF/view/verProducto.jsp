<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="es.taw.springsalidos.dto.ProductoDTO" %>
<%@ page import="es.taw.springsalidos.dto.TransaccionDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
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
    <title>Ver Producto</title>
</head>

<%
    ProductoDTO producto_ver = (ProductoDTO)request.getAttribute("producto_ver");
    List<TransaccionDTO> listaTransacciones = (List<TransaccionDTO>)request.getAttribute("listaTransacciones");
    String nombre_apellidos_vendedor="No existe";
    String precio_compra ="";


    Integer id_vendedor=0;

    for (int j=0; j<listaTransacciones.size();j++)
    {
        if(Objects.equals(listaTransacciones.get(j).getProducto().getId(), producto_ver.getId())
        && Objects.equals(listaTransacciones.get(j).getTipo(), "venta")
        )
        {
            nombre_apellidos_vendedor= listaTransacciones.get(j).getPersona().getNombre() +" " + listaTransacciones.get(j).getPersona().getApellidos();
            id_vendedor= listaTransacciones.get(j).getPersona().getId();
        }
    }

    if(producto_ver.getPrecioCompra()==null)
    {precio_compra="En puja";}
    else{precio_compra= (Double.toString(producto_ver.getPrecioCompra())); precio_compra = precio_compra +"€";}


%>
<body>
<h2>Datos del Producto</h2>

<p>

    <a href="/administrador/">Inicio</a>
    <br><br>


<table class="default" border="1">

    <tr>

        <td><b>Id Producto</b></td>

        <td><%= producto_ver.getId() %></td>


    </tr>

    <tr>

        <td><b>Nombre Producto</b></td>

        <td><%= producto_ver.getNombre() %></td>


    </tr>

    <tr>

        <td><b>Id Vendedor</b></td>

        <td><%= id_vendedor %></td>


    </tr>
    <tr>

        <td><b>Nombre Vendedor</b></td>
        <td><%= nombre_apellidos_vendedor %></td>


    </tr>
    <tr>

        <td><b>Fecha Venta</b></td>
        <td><%= producto_ver.getFechaVenta() %></td>


    </tr>
    <tr>

        <td><b>Precio Salida</b></td>
        <td><%= producto_ver.getPrecioSalida() %></td>


    </tr>

    <tr>

        <td><b>Precio Compra</b></td>
        <td><%= precio_compra%></td>


    </tr>

</table>


</p>





</body>
</html>
