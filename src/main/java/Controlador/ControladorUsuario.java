/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import ModeloDAO.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class ControladorUsuario extends HttpServlet {
    
    String crearUsuario = "vistas/crearUsuario.jsp";
    String login = "vistas/login.jsp";
    String index = "index.jsp";
    
    UsuarioDAO u_dao;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso = "";
        String action = request.getParameter("accion"); //variable para guardar que accion quiero hacer
        if(action.equalsIgnoreCase("Agregar")){
            u_dao = new UsuarioDAO();
            String nombre_usuario = request.getParameter("nombre_usuario");
            String apellido = request.getParameter("apellido");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");
            String contrasena = request.getParameter("contrasena");
            Usuario usuario = new Usuario(nombre_usuario, apellido,telefono, email, contrasena);
            u_dao.add(usuario);
            u_dao.closeConnection();
            u_dao.closeResources();
            acceso=index;
        } 
        
        else if(action.equalsIgnoreCase("cerrar")){
            // Obtener la sesión actual
            HttpSession session = request.getSession(false);
    
            if (session != null) {
                // Cerrar la sesión
                session.invalidate();
                acceso=login;
            }
            acceso=index;
            
        }
        
        response.sendRedirect(acceso);
        return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");
        if(action.equalsIgnoreCase("ACCEDER")){
            u_dao = new UsuarioDAO();
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String contrasena = request.getParameter("contrasena");
            
            if (session == null || session.getAttribute("id") == null) {
                // No hay sesión iniciada o no se ha establecido la variable de sesión "username"
                Usuario usuario = u_dao.logueo(email, contrasena);
                u_dao.closeConnection();
                u_dao.closeResources();
                if(usuario.getId_usuario() != 0 ){
                    session = request.getSession();
                    session.setAttribute("id", usuario.getId_usuario());
                    String rol = "cliente";
                    if(usuario.getRol() != null && usuario.getRol().equalsIgnoreCase("admin")){
                        rol = "admin";
                    }
                    session.setAttribute("rol",  rol);
                    acceso=index;
                }else{
                    acceso=login;
                }
            } else {
                acceso=login;
            }
        }
        
        response.sendRedirect(acceso);
        return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
