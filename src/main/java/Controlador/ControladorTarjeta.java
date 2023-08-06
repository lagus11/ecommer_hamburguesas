/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Tarjeta;
import ModeloDAO.TarjetaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/ControladorTarjeta")
public class ControladorTarjeta extends HttpServlet {

    TarjetaDAO t_dao;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorTarjeta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorTarjeta at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
            t_dao = new TarjetaDAO();
            int id_usuario = Integer.parseInt(request.getSession().getAttribute("id") + "");
            String numero = request.getParameter("numero");
            String fecha_caducidad = request.getParameter("fecha_caducidad");
            String titular = request.getParameter("titular");
            String emisor = request.getParameter("emisor");
            Tarjeta tarjeta = new Tarjeta(id_usuario, numero, fecha_caducidad, titular, emisor);
            t_dao.add(tarjeta); 
            t_dao.closeConnection();
            t_dao.closeResources();
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
