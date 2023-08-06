<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
   HttpSession sessionLogin = request.getSession(false);
   if (sessionLogin == null || sessionLogin.getAttribute("id") == null || sessionLogin.getAttribute("rol") != "admin") {
       // Si no hay una sesión iniciada, redirigir al usuario a otra página
       response.sendRedirect("/Hamburguesas/vistas/login.jsp");
   }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Opciones</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/opciones.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    
    <main class="contenedorPrincipal">
        <div class="contenedor">
            <h2 class="title">Opciones</h2>
            <a href="/Hamburguesas/vistas/adminUsuarios.jsp"><button class="btn">Usuarios</button></a>
            <a href="/Hamburguesas/vistas/adminPedidos.jsp"><button class="btn">Pedidos</button></a>
            <a href="/Hamburguesas/vistas/adminReservaciones.jsp"><button class="btn">Reservaciones</button></a>
            <a href="/Hamburguesas/vistas/adminProductos.jsp"><button class="btn">Productos</button></a>
        </div>
     </main>

    <!-- llamo componente footer -->
    <%@ include file="footer.jsp" %>

</body>
</html>


