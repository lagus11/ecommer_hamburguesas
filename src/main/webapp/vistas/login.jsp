
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   HttpSession sessionLogin = request.getSession(false);
   if (sessionLogin != null && sessionLogin.getAttribute("id") != null) {
       // Si hay una sesión iniciada, redirigir al usuario a otra página
       response.sendRedirect("/Hamburguesas/index.jsp");
   }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/header.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/login.css">
    <link rel="stylesheet" type="text/css" href="/Hamburguesas/css/footer.css">
</head>
<body>
    <!-- llamo componente header -->
    <%@include file="header.jsp" %>
    
    <div class="contenedorPrincipal">
        <form class="contenedor" method="post" action="/Hamburguesas/ControladorUsuario">
         <div class="contenedor_partSuperior">
           <img
             class="icono_login"
             src="/Hamburguesas/imagenes/logo2.png"
             alt="icono login"
           />
           <h1 class="titulo_login">BIENVENIDO</h1>
         </div>
         <div class="contenedor_partInferior">
           <div class="input_usuario">
             <input
               placeholder="CORREO"
               type="email"
               name="email"
               pattern=".+@.+\.com"
               required
             />
           </div>
           <div class="input_contrasena">
             <input
               placeholder="CONTRASEÑA"
               type="password"
               name="contrasena"
               required
             />
             
           </div>
           <div class="div_button_acceder">
             <input type="submit" name="accion" value="ACCEDER" />
           </div>
           <div class="div_button_acceder">
             <a href="crearUsuario.jsp">
              <input type="button" value="CREAR CUENTA">
             </a>
           </div>
         </div>
       </form>
     </div>

    
    <!-- llamo componente footer -->
    <%@ include file="footer.jsp" %>

</body>
</html>
