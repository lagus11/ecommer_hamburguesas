
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Agregar Producto</title>
            <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
            <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/agregarProducto.css">
            <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
        </head>
<body>
     <!-- llamo componente header -->
    <%@ include file="./header.jsp" %>
     
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.Iterator" %>
    <%@ page import="Modelo.Categoria" %>
    <%@ page import="ModeloDAO.CategoriaDAO" %>

    <main class="contenedorPrincipal">
       <form id="formulario" class="contenedor" method="post" action="/Hamburguesas/ControladorProducto" >
         <div class="contenedor_partSuperior">
           <img
             class="icono"
             src="/Hamburguesas/imagenes/logo2.png"
             alt="icono"
           />
           <h1 class="titulo_tarjeta">CREAR PRODUCTO</h1>
         </div>
         <div class="contenedor_partInferior">
            <div class="input_producto">
             <input
               placeholder="NOMBRE"
               type="text"
               name="nombre_producto"
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
               required
             />
            </div>
            <div class="input_producto">
            <input
              placeholder="PRECIO"
              type="number"
              name="precio"
              required
            />
           </div>
           <div class="input_producto">
            <input
              placeholder="Estado"
              type="text"
              name="estado"
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
             <input type="submit" name="accion" value="Agregar" />
           </div>
         </div>
       </form>
     </main>
         

     <!-- llamo componente footer -->
    <%@ include file="./footer.jsp" %>
</body>
</html>
