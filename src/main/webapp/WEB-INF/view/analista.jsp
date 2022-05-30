<%@ page import="es.taw.springsalidos.entity.AnalisisEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.taw.springsalidos.dto.PersonaDTO" %>
<%@ page import="es.taw.springsalidos.dto.AnalisisDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: gil
  Date: 28/5/22
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Analista</title>
</head>
<%
    PersonaDTO persona = (PersonaDTO)session.getAttribute("persona");
    List<AnalisisDTO> analisis = (List)request.getAttribute("analisis");

    SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
%>
<body>
    <h1>Bienvenido analista</h1>
    <a href="/cerrarSesion">Cerrar sesión</a>
    <h2>Datos personales</h2>
    <p>
        Nombre: <%= persona.getNombre() %></br>
        Apellidos: <%= persona.getApellidos() %></br>
        Email: <%= persona.getEmail() %>
    </p>
    <a href="/analista/generador">Generar informe</a>
    <h2>Informes generados</h2>
    <%
        if (analisis == null || analisis.isEmpty()) {
    %>
        <p style="color:red">No hay ningún informe disponible</p>
    <%
        } else {
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>Nº INFORME</th>
                    <th>DESCRIPCIÓN</th>
                    <th>FECHA INICIO</th>
                    <th>FECHA FIN</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < analisis.size(); i++) {
                        String fechaInicio = fecha.format(analisis.get(i).getFechaInicio());
                        String fechaFinal = fecha.format(analisis.get(i).getFechaFinal());
                %>
                        <tr>
                            <td><%= analisis.get(i).getId() %></td>
                            <td><%= analisis.get(i).getDescripcion() %></td>
                            <td><%= fechaInicio %></td>
                            <td><%= fechaFinal %></td>
                            <td><a href="#">Ver</a></td>
                            <td><a href="/analista/<%= analisis.get(i).getId() %>/editarInforme">Editar</a></td>
                            <td><a href="/analista/<%= analisis.get(i).getId() %>/borrarInforme">Borrar</a></td>
                        </tr>
                <%
                    }
                %>
            </tbody>
    <%
        }
    %>
</body>
</html>
