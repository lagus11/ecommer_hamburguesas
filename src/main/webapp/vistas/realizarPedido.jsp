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
    <%@ page import="Modelo.Carrito" %>
    <%@ page import="ModeloDAO.CarritoDAO" %>
    <%@ page import="Modelo.Direccion" %>
    <%@ page import="ModeloDAO.DireccionDAO" %>


    <!-- obtengo el total  -->
    <%
        double total = 0.0;
        CarritoDAO cao = new CarritoDAO();
        List<Carrito>listProducto = cao.listarProducto(sessionLogin.getAttribute("id") + "");
        Iterator<Carrito>iterProducto=listProducto.iterator();
        Carrito carrito = null;
        while(iterProducto.hasNext()){
            carrito = iterProducto.next();
            total += carrito.getProducto().getPrecio();
        }
        cao.closeConnection();
        cao.closeResources();
        // Reiniciar el iterador
        iterProducto = listProducto.iterator();

    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Realizar Pedido</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/realizarPedido.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    
    <main>
        <section class="seccion-inicio" id="inicio">
            <div class="contenedor">
                <form class="form" method="post">
                <div class="contenedor_partSuperior">
                  <h1 class="titulo">Realizar pedido</h1>
                </div>
                <div class="contenedor_partInferior">
                    
                    <h2 class="totalPagar">El total a pagar es</h2>
                    <h2 class="totalPagar precio">$<%=total %></h2>
               
                    <input
                      placeholder="TOTAL"
                      type="text"
                      name="monto_pago"
                      id="monto_pago"
                      value="<%=total %>"
                      required
                      hidden
                    />
               
                    <label id="label_tarjeta">Tarjeta</label>
                  <div class="input_tarjeta" id="input_tarjeta">
                      
                    <select name="id_tarjeta" id="id_tarjeta">
                         <%
                            TarjetaDAO tao = new TarjetaDAO();
                            List<Tarjeta>listTarjeta = tao.listar(sessionLogin.getAttribute("id") + "");
                            Iterator<Tarjeta>iterTarjeta=listTarjeta.iterator();
                            Tarjeta tarjeta = null;
                            while(iterTarjeta.hasNext()){
                                tarjeta = iterTarjeta.next();
                            %>
                            <option value="<%= tarjeta.getId_tarjeta()%>"><%= tarjeta.getEmisor()%> - <%= tarjeta.getTitular()%> - <%= tarjeta.getNumero()%></option>
                        <%
                            }
                            tao.closeConnection();
                            tao.closeResources();
                        %>
                    </select>
                 </div>
                    <div class="contenedor_efectivo">
                        <label>   
                            <input type="checkbox" id="efectivo" name="efectivo" onchange="ocultarTarjeta()"/> Pago Efectivo 
                        </label>
                    </div>
                 <label>Dirección</label>
                 <div class="input_tarjeta">
                      
                    <select name="direccion" id="direccion" required>
                         <%
                            DireccionDAO dao = new DireccionDAO();
                            List<Direccion>list = dao.listar(sessionLogin.getAttribute("id") + "");
                            Iterator<Direccion>iter=list.iterator();
                            Direccion direccion = null;
                            while(iter.hasNext()){
                                direccion = iter.next();
                            %>
                            <option value="<%= direccion.getComunidad()%>-<%= direccion.getNumero_exterior()%>"><%= direccion.getComunidad()%> - <%= direccion.getNumero_exterior()%></option>
                        <%
                            }
                            dao.closeConnection();
                            dao.closeResources();
                        %>
                    </select>
                  </div>
                  <div class="div_button_acceder">
                    <input type="button" name="accion" value="Agregar" onclick="enviarPedido()" />
                  </div>
                </div>
                </div>
              </form>
            </div>
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
                            while(iterProducto.hasNext()){
                                carrito = iterProducto.next();
                            
                        %>
                        <tr>
                            <td><%= carrito.getProducto().getNombre_producto()%></td>
                            <td>$<%= carrito.getProducto().getPrecio()%></td>
                            <td><%= carrito.getProducto().getDescripcion_producto()%></td>
                            <td>
                                <textarea id="comentario_<%=carrito.getId_carrito()%>" name="comentario_<%=carrito.getId_carrito()%>" rows="4" cols="30"></textarea>
                            </td>
                        </tr>
                            <%
                            }
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
    <script src="/Hamburguesas/js/realizarPedido.js"></script>
</body>
</html>


