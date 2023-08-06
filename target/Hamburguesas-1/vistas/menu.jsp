
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
         <h1 class="categoria-titulo">Que se te antoja hoy?</h1>
         
        <section class="seccion-categorias">
            <h2 class="categoria-titulo">Hot dogs</h2>
            <div class="contenedor-tarjetas">  
                <%
                    ProductoDAO dao = new ProductoDAO();
                    List<Producto>listC_hot_dogs = dao.listarCategoria("Hot dogs");
                    Iterator<Producto>iter=listC_hot_dogs.iterator();
                    Producto product = null;
                    while(iter.hasNext()){
                        product = iter.next();
                    %>
                    

                    <!-- Tarjeta hotdogs-->
	                <div class="categoria-tarjeta">
                            <img src="/Hamburguesas/imagenes/productos/<%= product.getImgUrl() %>" alt="" onerror="this.src='/Hamburguesas/imagenes/productos/sinImagen.jpg'">
	                    <h2><%= product.getNombre_producto()%></h2>
	                    <p><%= product.getDescripcion_producto()%></p>
	                    <p>$<%= product.getPrecio()%></p>

	                    <a href="./detalleProducto.jsp?id=<%= product.getId() %>"><button>Seleccionar</button></a>
                            <a href="/Hamburguesas/ControladorCarrito?accion=Agregar&id_producto=<%=product.getId()%>"><button>Agregar Carrito</button></a>
	                </div>
	                <!-- Fin tarjeta hotdogs-->
                    
                    <%
                    }
                    %>
            </div>
            
            
            <h2 class="categoria-titulo">Hamburguesas</h2>
            <div class="contenedor-tarjetas">  

                <%
        
                    List<Producto>listC_hamburguesas = dao.listarCategoria("Hamburguesas");
                    Iterator<Producto>iterhamburguesa=listC_hamburguesas.iterator();
                    Producto productHamburguesas = null;
                    while(iterhamburguesa.hasNext()){
                        productHamburguesas = iterhamburguesa.next();
                    %>
                    

                    <!-- Tarjeta hamburguesas-->
	                <div class="categoria-tarjeta">
	                    <img src="/Hamburguesas/imagenes/productos/<%= productHamburguesas.getImgUrl()%>" alt="" onerror="this.src='/Hamburguesas/imagenes/productos/sinImagen.jpg'">
	                    <h2><%= productHamburguesas.getNombre_producto()%></h2>
	                    <p><%= productHamburguesas.getDescripcion_producto()%></p>
	                    <p>$<%= productHamburguesas.getPrecio()%></p>

	                    <a href="./detalleProducto.jsp?id=<%= productHamburguesas.getId() %>"><button>Seleccionar</button></a>
                            <a href="/Hamburguesas/ControladorCarrito?accion=Agregar&id_producto=<%=productHamburguesas.getId()%>"><button>Agregar Carrito</button></a>
	                </div>
	                <!-- Fin tarjeta hamburguesas-->
                    
                    <%
                    }
                    %>
            </div>
            
            
            
            <h2 class="categoria-titulo">Refrescos</h2>
            <div class="contenedor-tarjetas">  

                <%
        
                    List<Producto>listC_refrescos = dao.listarCategoria("Refrescos");
                    Iterator<Producto>iter_refrescos=listC_refrescos.iterator();
                    Producto productRefrescos = null;
                    while(iter_refrescos.hasNext()){
                        productRefrescos = iter_refrescos.next();
                    %>
                    

                    <!-- Tarjeta Refrescos-->
	                <div class="categoria-tarjeta">
	                    <img src="/Hamburguesas/imagenes/productos/<%= productRefrescos.getImgUrl()%>" alt="" onerror="this.src='/Hamburguesas/imagenes/productos/sinImagen.jpg'">
	                    <h2><%= productRefrescos.getNombre_producto()%></h2>
	                    <p><%= productRefrescos.getDescripcion_producto()%></p>
	                    <p>$<%= productRefrescos.getPrecio()%></p>

	                    <a href="./detalleProducto.jsp?id=<%= productRefrescos.getId() %>"><button>Seleccionar</button></a>
                            <a href="../ControladorCarrito?accion=Agregar&id_producto=<%=productRefrescos.getId()%>"><button>Agregar Carrito</button></a>
	                </div>
	                <!-- Fin tarjeta Refrescos-->
                    
                    <%
                    }
                    %>
            </div>

            
            <h2 class="categoria-titulo">Extra</h2>
            <div class="contenedor-tarjetas">  

                <%
        
                    List<Producto>listC_extra = dao.listarCategoria("Extra");
                    Iterator<Producto>iter_extra=listC_extra.iterator();
                    Producto productExtra = null;
                    while(iter_extra.hasNext()){
                        productExtra = iter_extra.next();
                    %>
                    

                    <!-- Tarjeta extras-->
	                <div class="categoria-tarjeta">
	                    <img src="/Hamburguesas/imagenes/productos/<%= productExtra.getImgUrl()%>" alt="" onerror="this.src='/Hamburguesas/imagenes/productos/sinImagen.jpg'">
	                    <h2><%= productExtra.getNombre_producto()%></h2>
	                    <p><%= productExtra.getDescripcion_producto()%></p>
	                    <p>$<%= productExtra.getPrecio()%></p>

	                    <a href="./detalleProducto.jsp?id=<%= productExtra.getId() %>"><button>Seleccionar</button></a>
                            <a href="../ControladorCarrito?accion=Agregar&id_producto=<%=productExtra.getId()%>"><button>Agregar Carrito</button></a>
	                </div>
	                <!-- Fin tarjeta extras-->
                    
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
