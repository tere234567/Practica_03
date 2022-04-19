<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.coleccion_de_libros.categoria" %>
<%@page import="com.emergentes.coleccion_de_libros.Gestorcategorias" %>

<%
  if(session.getAttribute("agenda")==null){
  Gestorcategorias objeto1 = new Gestorcategorias();
  session.setAttribute("agenda",objeto1);
    }  
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL - categoria</title>
    </head>
    <body>
        <h1>Categorias</h1>
        <a href="Controller_categoria?op=nuevo">Nuevo</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.agenda.getLista()}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.categoria}</td>
                    <td><a href="Controller_categoria?op=modificar&id=${item.id}">Modificar</a></td>
                    <td><a href="Controller_categoria?op=eliminar&id=${item.id}">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
