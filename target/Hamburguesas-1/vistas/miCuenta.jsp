<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
   HttpSession sessionLogin = request.getSession(false);
   if (sessionLogin == null || sessionLogin.getAttribute("id") == null) {
       // Si no hay una sesión iniciada, redirigir al usuario a otra página
       response.sendRedirect("/Hamburguesas/vistas/login.jsp");
   }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Cuenta</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/miCuenta.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    
    <main class="contenedorPrincipal">
        <div class="contenedor">
            <h2 class="title">Mi Cuenta</h2>
            <a href="/Hamburguesas/vistas/direcciones.jsp"><button class="btn">Mis direcciones</button></a>
            <a href="/Hamburguesas/vistas/tarjeta.jsp"><button class="btn">Mis tarjetas</button></a>
            <a href="/Hamburguesas/vistas/pedido.jsp"><button class="btn">Mis pedidos</button></a>
            <a href="/Hamburguesas/vistas/reservacion.jsp"><button class="btn">Mis reservaciones</button></a>
        </div>
     </main>

    <!-- llamo componente footer -->
    <%@ include file="footer.jsp" %>

</body>
</html>



