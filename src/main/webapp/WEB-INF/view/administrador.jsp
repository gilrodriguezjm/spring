<%@ page import="es.taw.springsalidos.dto.PersonaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.springsalidos.dto.ProductoDTO" %><%--
  Created by IntelliJ IDEA.
  User: cristian
  Date: 28/5/22
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrador</title>
</head>

<%
    PersonaDTO persona = (PersonaDTO)session.getAttribute("persona");
    List<PersonaDTO> listaPersonas = (List)request.getAttribute("listaPersonas");
    List<ProductoDTO> listaProductos = (List)request.getAttribute("listaProductos");



%>
<body>
<h1>Bienvenido administrador</h1>
<h2>Datos personales</h2>
<p>
    Nombre: <%= persona.getNombre() %><br>
    Apellidos:  <%= persona.getApellidos() %><br>
    Email:  <%= persona.getEmail() %> <br>
    <a href="/cerrarSesion">Cerrar sesión</a>
</p>


<h2>Administración de personas</h2>

<%
    if (listaPersonas != null ) {
%>
<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>Email</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Rol</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <%
        for (int i = 0; i < listaPersonas.size(); i++) {

            if(persona.getIdPersona()!=(listaPersonas.get(i).getIdPersona()))
            {
    %>
    <tr>
        <td><%= listaPersonas.get(i).getIdPersona() %></td>
        <td><%= listaPersonas.get(i).getEmail() %></td>
        <td><%= listaPersonas.get(i).getNombre() %></td>
        <td><%= listaPersonas.get(i).getApellidos() %></td>
        <td><%= listaPersonas.get(i).getRol() %></td>
        <td><a href="/administrador/<%= listaPersonas.get(i).getIdPersona() %>/editarPersona">Editar</a></td>
        <td><a href="/administrador/<%= listaPersonas.get(i).getIdPersona() %>/borrarPersona">Borrar</a></td>

    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<%
    }
%>



<h2>Administración de productos</h2>

<table border="1">
    <thead>
    <tr>
        <td>Id objeto</td>
        <td>Nombre objeto</td>
        <td>Precio salida</td>
        <td>Precio compra</td>
    </tr>
    </thead>
    <tbody>
    <%
        String compra;

        for(int i =0; i<listaProductos.size();i++){

            if(listaProductos.get(i).getPrecioCompra()==null)
            {compra="En venta";}
            else{compra= (Double.toString(listaProductos.get(i).getPrecioCompra())); compra = compra +"€";}




    %>
    <tr>
        <td><%= listaProductos.get(i).getId() %></td>
        <td><%= listaProductos.get(i).getNombre()%></td>
        <td><%= listaProductos.get(i).getPrecioSalida() %>€</td>
        <td><%= compra %></td>
        <td><a href="/administrador/editarProducto/<%= listaProductos.get(i).getId()  %>">Editar</a></td>
        <td><a href="/administrador/borrarProducto/<%= listaProductos.get(i).getId()  %>">Borrar</a></td>



    </tr>
    <%
        }
    %>
    </tbody>
</table><br>









</body>
</html>
