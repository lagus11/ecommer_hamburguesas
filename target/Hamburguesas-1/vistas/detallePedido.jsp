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
    <%@ page import="Modelo.Carrito" %>
    <%@ page import="ModeloDAO.CarritoDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi carrito</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/detallePedido.css">
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
                            <th>Producto</th>
                            <th>Precio</th>
                            <th>Descripción</th>
                            <th>Comentario</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            CarritoDAO cao = new CarritoDAO();
                            List<Carrito>list = cao.listarIdPedido(request.getParameter("id"));
                            Iterator<Carrito>iter=list.iterator();
                            Carrito carrito = null;
                            while(iter.hasNext()){
                                carrito = iter.next();
                            %>
                        <tr>
                            <td><%= carrito.getProducto().getNombre_producto()%></td>
                            <td>$<%= carrito.getProducto().getPrecio()%></td>
                            <td><%= carrito.getProducto().getDescripcion_producto()%></td>
                            <td class="comentario"><%= carrito.getComentario()%></td>
                            
                        </tr>
                            <%
                            }
                            cao.closeConnection();
                            cao.closeResources();
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


