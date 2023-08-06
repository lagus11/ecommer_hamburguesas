

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    // Verificar si hay una sesión iniciada
    boolean sesionIniciada = (request.getSession(false) != null && request.getSession().getAttribute("id") != null);
    String id =  request.getSession(false).getAttribute("id") + "";
%>
<script src="/Hamburguesas/js/menu.js" defer></script> 
<!DOCTYPE html>
<!-- Navegación del la página -->
    <div class="nav-contenedor">
        <nav>
            <div class="logo">
                <a href="/Hamburguesas/index.jsp">
                    <img src="/Hamburguesas/imagenes/logo2.png" alt="">
                </a>
                <h2>Hamburguesas</h2>
            </div>
            <h2 id="menu-boton">&#9776;</h2>
            <ul id="menu">
                <a href="/Hamburguesas/index.jsp"><li>Inicio</li></a>
                <a href="/Hamburguesas/vistas/menu.jsp"><li>Menú</li></a>
                <% if (sesionIniciada && request.getSession().getAttribute("rol") == "admin") { %>
                <a href="/Hamburguesas/vistas/opciones.jsp"><li>Opciones</li></a>
                <% } else if (sesionIniciada && request.getSession().getAttribute("rol") == "cliente") { %>
                <a href="/Hamburguesas/vistas/carrito.jsp"><li>Mi carrito</li></a>
                <a href="/Hamburguesas/vistas/miCuenta.jsp"><li>Mi Cuenta</li></a> 
                <% } %>
                <% if (sesionIniciada) { %>
                    <a href="/Hamburguesas/ControladorUsuario?accion=cerrar"><li>Cerrar Sesión</li></a>
                <% } else { %>
                <a href="/Hamburguesas/vistas/login.jsp"><li>Iniciar sesión</li></a>  
                <% } %>     
            </ul>
        </nav>
    </div>
            
       
    <!-- Fin navegación -->

