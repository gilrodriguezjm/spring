<%@ page import="es.taw.springsalidos.entity.PersonaEntity" %>
<%@ page import="es.taw.springsalidos.entity.ProductoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.springsalidos.dto.ProductoDTO" %>
<%@ page import="es.taw.springsalidos.dto.PersonaDTO" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: gil
  Date: 28/5/22
  Time: 09:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio</title>
</head>

<%

    String strError = (String)request.getAttribute("error");
    if (strError == null) strError = "";
    PersonaDTO persona = (PersonaDTO) request.getAttribute("persona");


%>


<body>

<h2> Bienvenido <%= persona.getNombre() %> </h2>

<p>Nombre : <%= persona.getNombre() %> </p>
<p>Apellidos : <%= persona.getApellidos() %> </p>
<p>Email : <%= persona.getEmail() %> </p>
<p>Fecha de nacimiento : <%= persona.getFecha_nacimiento() %> </p>
<p>Monedero acutal : <%= persona.getMonedero() %> € </p>
<p>Domicilio : <%= persona.getDomicilio() %> </p>
<p>Ciudad : <%= persona.getCiudad() %> </p>
<p>Sexo : <%= persona.getSexo() %> </p>

<h2> Mostrando las ventas del cliente : </h2>

<%
    List<ProductoDTO> ventas = (List<ProductoDTO>) request.getAttribute("ventas");
    if(ventas == null || ventas.isEmpty()){

%>
<h3>No se han encontrado ventas</h3>
<%
    }else{
%>

<table border="1">
    <thead>
        <tr>
            <td>Nombre</td>
            <td>Fecha de puesta a venta</td>
            <td>Precio salida</td>
            <td>Precio compra</td>
        </tr>
    </thead>
    <tbody>
        <%

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for(ProductoDTO p : ventas){
        %>
        <tr>
            <td><%= p.getNombre() %></td>
            <td><%= sdf.format(p.getFechaVenta()) %></td>
            <td><%= p.getPrecioSalida() %> €</td>
            <%
                if(p.getPrecioCompra() == null){
            %>
            <td>0 €</td>
            <%
                }else{
            %>
            <td><%= p.getPrecioCompra() %> €</td>
            <%
                }
            %>

        <%
            }
        %>

        </tr>

    </tbody>
</table>

<%
    }
%>

</body>
</html>
