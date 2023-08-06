/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Reservacion;
import ModeloDAO.ReservacionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControladorReservacion extends HttpServlet {

    ReservacionDAO r_dao;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorReservacion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorReservacion at " + request.getContextPath() + "</h1>");
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
        if (action.equalsIgnoreCase("eliminar")){
            r_dao = new ReservacionDAO();
            int id_reservacion = Integer.parseInt(request.getParameter("id"));
            Reservacion reservacion = new Reservacion();
            
            reservacion.setId_reservacion(id_reservacion);
            r_dao.eliminar(id_reservacion);
            r_dao.closeConnection();
            r_dao.closeResources();
            // Redirigir a la página anterior sin incluir el nombre del controlador en la URL
            response.sendRedirect(request.getHeader("Referer"));
            return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
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
            
        if (action.equalsIgnoreCase("Agregar")){
            r_dao = new ReservacionDAO();
            int id_usuario = Integer.parseInt(request.getSession().getAttribute("id") + "");
            String nombre_reservacion = request.getParameter("nombre_reservacion");
            String fecha_reservacion = request.getParameter("fecha_reservacion");
            String hora = request.getParameter("hora");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            
            Reservacion reservacion = new Reservacion(id_usuario, nombre_reservacion, fecha_reservacion, hora, direccion, telefono);
            r_dao.add(reservacion); 
            r_dao.closeConnection();
            r_dao.closeResources();
            // Redirigir a la página anterior sin incluir el nombre del controlador en la URL
            System.out.println("todo bien");
            response.sendRedirect(request.getHeader("Referer"));
            return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
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
