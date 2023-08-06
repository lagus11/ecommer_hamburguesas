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
    <%@ page import="Modelo.Tarjeta" %>
    <%@ page import="ModeloDAO.TarjetaDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tarjetas</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/tarjeta.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    
    <main>
        <section class="seccion-inicio" id="inicio">
            <div class="contenedor">
                <form class="form" method="post" action="/Hamburguesas/ControladorTarjeta">
                <div class="contenedor_partSuperior">
                  <h1 class="titulo">Registrar Tarjeta</h1>
                </div>
                <div class="contenedor_partInferior">
                 
                  <div class="input_tarjeta">
                    <input
                      placeholder="NUMERO TARJETA"
                      type="text"
                      name="numero"
                      pattern="\d{16}"
                      required
                    />
                  </div>
                  <div class="input_tarjeta">
                    <input
                      placeholder="FECHA CADUCIDAD"
                      type="date"
                      name="fecha_caducidad"
                      required
                    />
                  </div>
                  <div class="input_tarjeta">
                    <input
                      placeholder="TITULAR"
                      type="text"
                      name="titular"
                      required
                    />
                  </div>
                  <div class="input_tarjeta">
                    <input
                      placeholder="EMISOR"
                      type="text"
                      name="emisor"
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
                            <th>Numero Tarjeta</th>
                            <th>Fecha caducidad</th>
                            <th>Nombre Titular</th>
                            <th>Emisor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            TarjetaDAO tao = new TarjetaDAO();
                            List<Tarjeta>list = tao.listar(sessionLogin.getAttribute("id") + "");
                            Iterator<Tarjeta>iter=list.iterator();
                            Tarjeta tarjeta = null;
                            while(iter.hasNext()){
                                tarjeta = iter.next();
                            %>
                        <tr>
                            <td><%= tarjeta.getNumero()%></td>
                            <td><%= tarjeta.getFecha_caducidad()%></td>
                            <td><%= tarjeta.getTitular()%></td>
                            <td><%= tarjeta.getEmisor()%></td>
                        </tr>
                            <%
                            }
                            tao.closeConnection();
                            tao.closeResources();
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


