<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
   HttpSession sessionLogin = request.getSession(false);
   if (sessionLogin == null || sessionLogin.getAttribute("id") == null) {
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
    <title>Mis reservaciones</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/reservacion.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    
    <main>
        <section class="seccion-inicio" id="inicio">
            <div class="contenedor">
                <form class="form" method="post" action="/Hamburguesas/ControladorReservacion">
                <div class="contenedor_partSuperior">
                  <h1 class="titulo">Registrar Reservación</h1>
                </div>
                <div class="contenedor_partInferior">
                 
                  <div class="input_reservacion">
                    <input
                      placeholder="NOMBRE RESERVACIÓN"
                      type="text"
                      name="nombre_reservacion"
                      required
                    />
                  </div>
                  <div class="input_reservacion">
                    <input
                      placeholder="FECHA RESERVACIÓN"
                      type="date"
                      name="fecha_reservacion"
                      required
                    />
                  </div>
                  <div class="input_reservacion">
                    <input
                      placeholder="HORA RESERVACIÓN"
                      type="time"
                      name="hora"
                      required
                    />
                  </div>
                  <div class="input_reservacion">
                    <input
                      placeholder="DIRECCIÓN"
                      type="text"
                      name="direccion"
                      required
                    />
                  </div>
                  <div class="input_reservacion">
                    <input
                      placeholder="TELEFONO"
                      type="number"
                      name="telefono"
                      required
                    />
                  </div>                  
   
                  <div class="div_button_acceder">
                    <input type="submit" name="accion" value="Agregar" />
                  </div>
                </div>
                </div>
              </form>
            </div>
            <div class="contenedor">
                <table class="styled-table">
                    <thead>
                        <tr>
                            <th>Nombre reservación</th>
                            <th>Fecha reservación</th>
                            <th>Hora</th>
                            <th>Dirección</th>
                            <th>Estatus</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                       <%
                            ReservacionDAO rao = new ReservacionDAO();
                            List<Reservacion>list = rao.listar(sessionLogin.getAttribute("id") + "");
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
                            <td><a href="/Hamburguesas/ControladorReservacion?accion=eliminar&id=<%= reservacion.getId_reservacion()%>"><button class="accion_btn_cancelar">Cancelar<button</a></td>  
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


