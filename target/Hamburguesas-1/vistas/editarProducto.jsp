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
    <%@ page import="Modelo.Categoria" %>
    <%@ page import="ModeloDAO.CategoriaDAO" %>
    <%@ page import="Modelo.Producto" %>
    <%@ page import="ModeloDAO.ProductoDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tarjetas</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/editarProducto.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    <%
        ProductoDAO p_dao = new ProductoDAO();
        int id_producto = Integer.parseInt((String)request.getSession().getAttribute("id_producto"));
        Producto producto = (Producto)p_dao.listProducto(id_producto);
        p_dao.closeConnection();
        p_dao.closeResources();
    %>
    <main>
        <section class="seccion-inicio" id="inicio">
            <div class="contenedor">
                <form class="form" method="post" action="/Hamburguesas/ControladorProducto">
                <div class="contenedor_partSuperior">
                  <h1 class="titulo">Editar Producto</h1>
                </div>
                <div class="contenedor_partInferior">
                 

                  <div class="input_producto">
                    <input
                        placeholder="NOMBRE"
                        type="text"
                        name="nombre_producto"
                        value="<%=producto.getNombre_producto() %>"
                        required
                      />
                  </div> 
                  <div class="input_producto">
                    <input
                        placeholder="DESCRIPCIÓN"
                        type="text"
                        name="descripcion_producto"
                        value="<%=producto.getDescripcion_producto() %>"
                        required
                      />
                  </div> 
                  <div class="input_producto">
                    <input
                        placeholder="PRECIO"
                        type="number"
                        name="precio"
                        value="<%=producto.getPrecio() %>"
                        required
                      />
                  </div> 
                  <div class="input_producto">
                    <input
                        placeholder="Estado"
                        type="text"
                        name="estado"
                        value="<%=producto.getEstado() %>"
                        required
                      />
                  </div> 
                  <!--
                  <div class="input_producto">
                    <input 
                        type="file" 
                        id="imgUrl" 
                        name="imgUrl" 
                        accept="image/*"
                        >
                  </div>
                   -->
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


