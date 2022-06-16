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

<a href="/administrador/">Inicio</a>
<br><br>

<table border="1">
    <thead>
    <tr>
        <th>
            Id Persona: <%= persona_editar.getIdPersona()%><br>
            Email:  <%= persona_editar.getEmail() %> <br>
        </th>

    </tr>
    </thead>

</table>


<p>

    <form:form method="POST" action="/administrador/actualizarPersona/" modelAttribute="persona_editar">
        <form:hidden path="idPersona" />
        <form:hidden path="email" />

        Nombre</br>
        <form:input path="nombre" /> </br>
        </br>

        Apellidos</br>
        <form:input path="apellidos" /> </br>
        </br>

        Fecha Nacimiento</br>
        <form:input path="fecha_nacimiento" /> </br>
        </br>

        Domicilio</br>
        <form:input path="domicilio" /> </br>
        </br>

        Ciudad</br>
        <form:input path="ciudad" /> </br>
        </br>

        Sexo</br>
        <form:input path="sexo" /> </br>
        </br>

        Rol</br>
        <form:radiobutton path="rol" value="Administrador"/>
        <label>Administrador</label>
        <form:radiobutton path="rol" value="Usuario"/>
        <label>Usuario</label>
        <form:radiobutton path="rol" value="Marketing"/>
        <label>Marketing</label>
        <form:radiobutton path="rol" value="Analista"/>
        <label>Analista</label>
        </br>
        </br>
        <form:button>Editar Persona</form:button>
    </form:form>



</p>




</body>
</html>
