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
    <%@ page import="Modelo.Direccion" %>
    <%@ page import="ModeloDAO.DireccionDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Opciones</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/direcciones.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    
    <main>
        <section class="seccion-inicio" id="inicio">
            <div class="contenedor">
                <form class="form" method="post" action="/Hamburguesas/ControladorDireccion">
                <div class="contenedor_partSuperior">
                  <h1 class="titulo">Crear Dirección</h1>
                </div>
                <div class="contenedor_partInferior">
                 
                  <div class="input_direccion">
                    <input
                      placeholder="COMUNIDAD"
                      type="text"
                      name="comunidad"
                      required
                    />
                  </div>
                    
                  <div class="input_direccion">
                    <input
                      placeholder="NUMERO EXTERIOR"
                      type="text"
                      name="numero_exterior"
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
                            <th>Comunidad</th>
                            <th>Numero exterior</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            DireccionDAO dao = new DireccionDAO();
                            List<Direccion>list = dao.listar(sessionLogin.getAttribute("id") + "");
                            Iterator<Direccion>iter=list.iterator();
                            Direccion direccion = null;
                            while(iter.hasNext()){
                                direccion = iter.next();
                            %>
                        <tr>
                            <td><%= direccion.getComunidad()%></td>
                            <td><%= direccion.getNumero_exterior()%></td>
                            <td class="td_accion"><a href="/Hamburguesas/ControladorDireccion?accion=editar&id=<%= direccion.getId_direccion()%>"><button class="accion_btn_editar">Editar</button></a><a href="/Hamburguesas/ControladorDireccion?accion=eliminar&id=<%= direccion.getId_direccion()%>"><button class="accion_btn_eliminar">Eliminar</button></a></td>  
                        </tr>
                            <%
                            }
                            dao.closeConnection();
                            dao.closeResources();
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


