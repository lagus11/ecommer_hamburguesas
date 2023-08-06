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
    <%@ page import="Modelo.Usuario" %>
    <%@ page import="ModeloDAO.UsuarioDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrar clientes</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/adminUsuarios.css">
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
                            <th>Nombre usuario</th>
                            <th>Apellido</th>
                            <th>Telefono</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            UsuarioDAO uao = new UsuarioDAO();
                            List<Usuario>list = uao.listar();
                            Iterator<Usuario>iter=list.iterator();
                            Usuario usuario = null;
                            while(iter.hasNext()){
                                usuario = iter.next();
                            %>
                        <tr>
                            <td><%= usuario.getNombre_usuario()%></td>
                            <td><%= usuario.getApellido()%></td>
                            <td><%= usuario.getTelefono()%></td>
                            <td><%= usuario.getEmail()%></td>
                        </tr>
                            <%
                            }
                            uao.closeConnection();
                            uao.closeResources();
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


