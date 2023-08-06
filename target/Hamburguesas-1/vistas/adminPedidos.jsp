<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   HttpSession sessionLogin = request.getSession(false);
   if (sessionLogin == null || sessionLogin.getAttribute("id") == null || sessionLogin.getAttribute("rol") != "admin" ) {
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
    <title>Administrar pedidos</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/adminPedidos.css">
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
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Dirección</th>
                            <th>Monto pago</th>
                            <th>Estado actual</th>
                            <th>Detalle</th>
                            <th>Acción estado</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            PedidoDAO pao = new PedidoDAO();
                            List<Pedido>list = pao.listar();
                            Iterator<Pedido>iter=list.iterator();
                            Pedido pedido = null;
                            while(iter.hasNext()){
                                pedido = iter.next();
                            %>
                        <tr>
                            <td><%= pedido.getFecha()%></td>
                            <td><%= pedido.getHora()%></td>
                            <td><%= pedido.getDireccion()%></td>
                            <td>$<%= pedido.getMonto_pago()%></td>
                            <td><%= pedido.getEstado()%></td>
                            <td><a href="/Hamburguesas/vistas/detallePedido.jsp?id=<%= pedido.getId_pedido()%>"><button class="accion_btn_detalle">Ver detalle</button></a></td>
                            <td>
                                <select class="select" id="estado_<%=pedido.getId_pedido()%>">
                                    <option value="solicitado">solicitado</option>
                                    <option value="enviado">enviado</option>
                                    <option value="finalizado">finalizado</option>
                                </select>
                            </td>
                            <td class="td_accion"><button class="accion_btn_actualizar" onclick="actualizarPedido(<%=pedido.getId_pedido() %>)">Actualizar</button></td>  
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
    
    <!-- js para mandar los datos del formulario a js -->
    <script src="/Hamburguesas/js/actualizarPedido.js"></script>

</body>
</html>


