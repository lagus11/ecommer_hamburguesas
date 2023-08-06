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
    <%@ page import="Modelo.Pedido" %>
    <%@ page import="ModeloDAO.PedidoDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi carrito</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/pedido.css">
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
                            <th>Metodo Pago</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Estado</th>
                            <th>Monto</th>
                            <th>Dirección</th>
                            <th>Detalle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            PedidoDAO pao = new PedidoDAO();
                            List<Pedido>list = pao.listar(sessionLogin.getAttribute("id") + "");
                            Iterator<Pedido>iter=list.iterator();
                            Pedido pedido = null;
                            while(iter.hasNext()){
                                pedido = iter.next();
                                
                                String tipoPago = "Tarjeta";
                                
                                if(pedido.getId_tarjeta() == 0){
                                    tipoPago = "Efectivo";
                                }
                            %>
                        <tr>
                            <td><%= tipoPago%></td>
                            <td><%= pedido.getFecha()%></td>
                            <td><%= pedido.getHora()%></td>
                            <td><%= pedido.getEstado()%></td>
                            <td>$<%= pedido.getMonto_pago()%></td>
                            <td><%= pedido.getDireccion()%></td>
                            <td><a href="/Hamburguesas/vistas/detallePedido.jsp?id=<%= pedido.getId_pedido()%>"><button class="accion_btn_ver">Ver detalle</button></a></td>  
                        </tr>
                            <%
                            }
                            pao.closeConnection();
                            pao.closeResources();
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


