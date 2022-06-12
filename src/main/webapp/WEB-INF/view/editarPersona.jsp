<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.springsalidos.dto.PersonaDTO" %>
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
    <title>Editar Persona</title>
</head>

<%
    PersonaDTO persona_editar = (PersonaDTO)request.getAttribute("persona_editar");
    String message = (String)request.getAttribute("Control");



%>
<body>
<h2>Datos Usuario a editar</h2>
<% if (message!=null){

    %>

<p style="color:green;"><%= message %></p>

<%
    }
%>
<p>
    Nombre: <%= persona_editar.getNombre() %><br>
    Apellidos:  <%= persona_editar.getApellidos() %><br>
    Email:  <%= persona_editar.getEmail() %> <br>
</p>



<form:form method="POST" action="/administrador/actualizarPersona/" modelAttribute="persona">
    <form:hidden path="id" />
    <form:hidden path="monedero" />
    <form:hidden path="sexo" />
    <form:hidden path="password" />
    <form:hidden path="rol" />

    <label>Descripción:</label>
    <br>
    <form:textarea path="descripcion" rows="5" col="12" required="required"/>
    <br><br>
    <form:button>Editar informe</form:button>
</form:form>




</body>
</html>
