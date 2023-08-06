

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hamburguesas</title>
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/index.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
</head>
<body>

    <!-- llamo componente header -->
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.Iterator" %>
    <%@ page import="Modelo.Producto" %>
    <%@ page import="ModeloDAO.ProductoDAO" %>
    
    
    <%@ include file="./vistas/header.jsp" %>
    
    <main>
        
        <!--Sección de inicio-->
        <section class="seccion-inicio" id="inicio">

            <div class="tarjeta-inicio">
                <img src="./imagenes/logo2.png" alt="">
            </div>

            <div class="img-contenedor">

                <script src="/Hamburguesas/js/carrusel.js" defer></script>  

                <div class="carrusel">
                    <img src="./imagenes/principal/hamburguesa1.jpg" alt="">
                    <img src="./imagenes/principal/hamburguesa2.jpg" alt="">
                    <img src="./imagenes/principal/hamburguesa3.jpg" alt="">
                    <img src="./imagenes/principal/hamburguesa4.jpg" alt="">
                    <img src="./imagenes/principal/hamburguesa5.jpg" alt="">
                </div>
                
                <div class="contenedor-nav-img">
                    <ul class="navegador-img">
                        <li class="carrusel-btn">&#8718;</li>
                        <li class="carrusel-btn">&#8718;</li>
                        <li class="carrusel-btn">&#8718;</li>
                        <li class="carrusel-btn">&#8718;</li>
                        <li class="carrusel-btn">&#8718;</li>
                    </ul>
                </div>

            </div>

        </section>
        <!-- Fin sección de inicio-->

        <section class="seccion-categorias">

            <h1 class="categoria-titulo">Que se te antoja hoy?</h1>
            <div class="contenedor-tarjetas">  
                <%
                    ProductoDAO dao = new ProductoDAO();
                    List<Producto>list = dao.listarLimit(3);
                    Iterator<Producto>iter=list.iterator();
                    Producto product = null;
                    while(iter.hasNext()){
                        product = iter.next();
                    %>
                    <!-- Tarjeta hamburguesas-->
	                <div class="categoria-tarjeta">
	                    <img src="/Hamburguesas/imagenes/productos/<%= product.getImgUrl()%>" alt="" onerror="this.src='/Hamburguesas/imagenes/productos/sinImagen.jpg'">
	                    <h2><%= product.getNombre_producto()%></h2>
	                    <p><%= product.getDescripcion_producto()%></p>
                            <p><strong>$<%= product.getPrecio()%></strong></p>
	   

                            <a href="./vistas/detalleProducto.jsp?id=<%= product.getId() %>"><button>Seleccionar</button></a>
                            <a href="/Hamburguesas/ControladorCarrito?accion=Agregar&id_producto=<%=product.getId()%>"><button>Agregar Carrito</button></a>
	                </div>
	                <!-- Fin tarjeta hamburguesas-->
                    <%
                    }
                    dao.closeConnection();
                    dao.closeResources();
                    %>
            </div>
            <div class="contenedor_btn_ver_mas">
                <a href="./vistas/menu.jsp"><button class="btn_ver_mas">Ver más</button></a>
            </div>
        </section>
        <!-- Fin sección de categorías de postres -->
     
    </main>

            <!-- mapa, si se quiere otra direccion, cambiar las coordenadas longitud y latitud 21.661186425348106, -105.19838915535388 -->
    <div id="map">
        <iframe src="https://maps.google.com/maps?q=21.5114639485945,-104.89175460290504&z=15&output=embed" 
                width="100%" height="400px" frameborder="0" style="border:0" allowfullscreen></iframe>
    </div>

    <!-- llamo componente footer -->
    <%@ include file="./vistas/footer.jsp" %>
    
    
</body>
</html