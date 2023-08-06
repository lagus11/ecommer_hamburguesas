
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Menu</title>
            <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
            <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/menu.css">
            <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
        </head>
<body>
     <!-- llamo componente header -->
    <%@ include file="./header.jsp" %>
     
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.Iterator" %>
    <%@ page import="Modelo.Producto" %>
    <%@ page import="ModeloDAO.ProductoDAO" %>
    


     
     <main>
         <h1 class="categoria-titulo">Detalles Producto</h1>
         
        <section class="seccion-categorias">
            <div class="contenedor-tarjetas">  
                <%
                    ProductoDAO dao = new ProductoDAO();
                    List<Producto>list = dao.list(request.getParameter("id"));
                    Iterator<Producto>iter=list.iterator();
                    Producto product = null;
                    while(iter.hasNext()){
                        product = iter.next();
                    %>
                    
                    <!-- Tarjeta hamburguesas-->
	                <div class="categoria-tarjeta">
	                    <img src="/Hamburguesas/imagenes/productos/<%=product.getImgUrl()%>" alt="" onerror="this.src='/Hamburguesas/imagenes/productos/sinImagen.jpg'">
	                    <h2><%= product.getNombre_producto()%></h2>
	                    <p><%= product.getDescripcion_producto()%></p>
	                    <p><%= product.getPrecio()%></p>
	                    <p><%= product.getEstado()%></p>

	                    <a href="/Hamburguesas/ControladorCarrito?accion=Agregar&id_producto=<%=product.getId()%>"><button>Agregar Carrito</button></a>
	                </div>
	                <!-- Fin tarjeta hamburguesas-->
                    
                    <%
                    }
                    dao.closeConnection();
                    dao.closeResources();
                    %>
            </div>
            
          
            
        </section>
        <!-- Fin sección de categorías de postres -->
     </main>

     <!-- llamo componente footer -->
    <%@ include file="./footer.jsp" %>
</body>
</html>
