/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Direccion;
import ModeloDAO.DireccionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ControladorDireccion extends HttpServlet {

     DireccionDAO d_dao;
     String editarDireccion = "vistas/editarDirecciones.jsp";
     String direcciones = "vistas/direcciones.jsp";
     
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorDireccion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorDireccion at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        
        if (action.equalsIgnoreCase("eliminar")){
            d_dao = new DireccionDAO();
            int id_direccion = Integer.parseInt(request.getParameter("id"));
            Direccion direccion = new Direccion();
            
            direccion.setId_direccion(id_direccion);
            d_dao.eliminar(id_direccion);
            d_dao.closeConnection();
            d_dao.closeResources();
            // Redirigir a la página anterior sin incluir el nombre del controlador en la URL
            response.sendRedirect(request.getHeader("Referer"));
            return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
        } else if (action.equalsIgnoreCase("editar")){
                session = request.getSession();
                session.setAttribute("id_direccion", request.getParameter("id"));
                acceso = editarDireccion;
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
        String action = request.getParameter("accion"); //variable para guardar que accion quiero hacer
            
        if (action.equalsIgnoreCase("Agregar")){
            d_dao = new DireccionDAO();
            int id_usuario = Integer.parseInt(request.getSession().getAttribute("id") + "");
            String comunidad = request.getParameter("comunidad");
            String numero_exterior = request.getParameter("numero_exterior");
            Direccion direccion = new Direccion(id_usuario, comunidad, numero_exterior);
            d_dao.add(direccion); 
            d_dao.closeConnection();
            d_dao.closeResources();
            // Redirigir a la página anterior sin incluir el nombre del controlador en la URL
            response.sendRedirect(request.getHeader("Referer"));
            return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
        } else if (action.equalsIgnoreCase("Actualizar")){
                d_dao = new DireccionDAO();
                String comunidad = request.getParameter("comunidad") ;
                String numero_exterior = request.getParameter("numero_exterior");
             

                Direccion direccion = new Direccion();
                direccion.setId_direccion(Integer.parseInt((String)request.getSession().getAttribute("id_direccion")));
                direccion.setComunidad(comunidad);
                direccion.setNumero_exterior(numero_exterior);
               
                d_dao.edit(direccion); 
                d_dao.closeConnection();
                d_dao.closeResources();
                acceso = direcciones;
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
