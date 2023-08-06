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
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/adminProductos.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@ include file="header.jsp" %>
    
    <main>
        <section class="seccion-inicio" id="inicio">
            <div class="contenedor">
                <form class="form" enctype="multipart/form-data" id="formulario_producto">
                <div class="contenedor_partSuperior">
                  <h1 class="titulo">Registrar Producto</h1>
                </div>
                <div class="contenedor_partInferior">
                 

                  <div class="input_producto">
                    <input
                        placeholder="NOMBRE"
                        type="text"
                        name="nombre_producto"
                        id="nombre_producto"
                        required
                      />
                  </div> 
                  <div class="input_producto">
                    <select name="id_categoria" id="id_categoria">
                     <%
                        CategoriaDAO cao = new CategoriaDAO();
                        List<Categoria>list = cao.listar();
                        Iterator<Categoria>iter=list.iterator();
                        Categoria categoria = null;
                        while(iter.hasNext()){
                            categoria = iter.next();
                        %>
                        <option value="<%= categoria.getId_categoria()%>"><%= categoria.getNombre_categoria()%></option>
                    <%
                        }
                        cao.closeConnection();
                        cao.closeResources();
                    %>
                    </select>
                  </div>
                  <div class="input_producto">
                    <input
                        placeholder="DESCRIPCIÓN"
                        type="text"
                        name="descripcion_producto"
                        id="descripcion_producto"
                        required
                      />
                  </div> 
                  <div class="input_producto">
                    <input
                        placeholder="PRECIO"
                        type="number"
                        name="precio"
                        id="precio"
                        required
                      />
                  </div> 
                  <div class="input_producto">
                    <input
                        placeholder="Estado"
                        type="text"
                        name="estado"
                        id="estado"
                        required
                      />
                  </div> 
                  <div class="input_producto">
                    <input 
                        type="file" 
                        id="imgUrl" 
                        name="imgUrl" 
                        accept="image/*"
                        >
                  </div>
                   
                  <div class="div_button_acceder">
                      <input type="button" onclick="enviarFormulario()" value="Agregar"/>
                  </div>
                </div>
                </div>
              </form>
            </div>
            <div class="contenedor">
                <table class="styled-table">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Precio</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                         <%
                            ProductoDAO pao = new ProductoDAO();
                            List<Producto>listProducto = pao.listar();
                            Iterator<Producto>iterProducto=listProducto.iterator();
                            Producto producto = null;
                            while(iterProducto.hasNext()){
                                producto = iterProducto.next();
                            %>
                        <tr>
                            <td><%= producto.getNombre_producto()%></td>
                            <td><%= producto.getDescripcion_producto()%></td>
                            <td>$<%= producto.getPrecio()%></td>
                            <td><%= producto.getEstado()%></td>
                            <td class="td_accion"><a href="/Hamburguesas/ControladorProducto?accion=editar&id=<%= producto.getId_producto()%>"><button class="accion_btn_editar">Editar</button></a><a href="/Hamburguesas/ControladorProducto?accion=eliminar&id=<%= producto.getId_producto()%>"><button class="accion_btn_eliminar">Eliminar</button></a></td>  
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
    <script src="/Hamburguesas/js/formulario_producto.js"></script>
    
</body>
</html>


