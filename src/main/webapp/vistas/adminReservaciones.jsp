<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
   HttpSession sessionLogin = request.getSession(false);
   if (sessionLogin == null || sessionLogin.getAttribute("id") == null || sessionLogin.getAttribute("rol") != "admin") {
       // Si no hay una sesión iniciada, redirigir al usuario a otra página
       response.sendRedirect("/Hamburguesas/vistas/login.jsp");
   }
%>

    <%@ page import="java.util.List" %>
    <%@ page import="java.util.Iterator" %>
    <%@ page import="Modelo.Reservacion" %>
    <%@ page import="ModeloDAO.ReservacionDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrar Reservaciones</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/adminReservaciones.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    
    <main>
        <section class="seccion-inicio" id="inicio"> 
           <div class="contenedor">
                <table class="styled-table">
                    <thead>
                        <tr>
                            <th>Nombre reservación</th>
                            <th>Fecha reservación</th>
                            <th>Hora</th>
                            <th>Dirección</th>
                            <th>Telefono</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ReservacionDAO rao = new ReservacionDAO();
                            List<Reservacion>list = rao.listar();
                            Iterator<Reservacion>iter=list.iterator();
                            Reservacion reservacion = null;
                            while(iter.hasNext()){
                                reservacion = iter.next();
                            %>
                        <tr>
                            <td><%= reservacion.getNombre_reservacion()%></td>
                            <td><%= reservacion.getFecha_reservacion()%></td>
                            <td><%= reservacion.getHora()%></td>
                            <td><%= reservacion.getDireccion()%></td>
                            <td><%= reservacion.getTelefono()%></td>
                        </tr>
                            <%
                            }
                            rao.closeConnection();
                            rao.closeResources();
                            %>
            
                    </tbody>
                </table>
            </div>
        </section>
        <!-- Fin sección de inicio-->
     </main>

                    
                    
    <!-- llamo componente footer -->
    <%@ include file="footer.jsp" %>

</body>
</html>


