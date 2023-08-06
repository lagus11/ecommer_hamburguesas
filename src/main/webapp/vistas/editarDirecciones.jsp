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
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/editarDireccion.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    <%
        DireccionDAO d_dao = new DireccionDAO();
        int id_direccion = Integer.parseInt((String)request.getSession().getAttribute("id_direccion"));
        Direccion direccion = (Direccion)d_dao.list(id_direccion);
        d_dao.closeConnection();
        d_dao.closeResources();
    %>
    <main>
        <section class="seccion-inicio" id="inicio">
            <div class="contenedor">
                <form class="form" method="post" action="/Hamburguesas/ControladorDireccion">
                <div class="contenedor_partSuperior">
                  <h1 class="titulo">Editar Dirección</h1>
                </div>
                <div class="contenedor_partInferior">
                 
                  <div class="input_direccion">
                    <input
                      placeholder="COMUNIDAD"
                      type="text"
                      name="comunidad"
                      value="<%=direccion.getComunidad() %>"
                      required
                    />
                  </div>
                    
                  <div class="input_direccion">
                    <input
                      placeholder="NUMERO EXTERIOR"
                      type="text"
                      name="numero_exterior"
                      value="<%=direccion.getNumero_exterior() %>"
                      required
                    />
                  </div>
                  <div class="div_button_acceder">
                    <input type="submit" name="accion" value="Actualizar" />
                  </div>
                </div>
                </div>
              </form>
            </div>
        </section>
        <!-- Fin sección de inicio-->
     </main>

                    
                    
    <!-- llamo componente footer -->
    <%@ include file="footer.jsp" %>

</body>
</html>


