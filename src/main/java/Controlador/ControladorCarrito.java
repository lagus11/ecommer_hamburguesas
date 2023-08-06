/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Carrito;
import ModeloDAO.CarritoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControladorCarrito extends HttpServlet {

     CarritoDAO c_dao;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorCarrito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorCarrito at " + request.getContextPath() + "</h1>");
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
            
        if (action.equalsIgnoreCase("Agregar")){
            c_dao = new CarritoDAO();
            int id_producto  = Integer.parseInt(request.getParameter("id_producto"));
            int id_usuario = Integer.parseInt(request.getSession().getAttribute("id") + "");
            
            Carrito carrito = new Carrito();
            carrito.setId_producto(id_producto);
            carrito.setComentario("");
            
            c_dao.add(carrito, id_usuario); 
            c_dao.closeConnection();
            c_dao.closeResources();
            // Redirigir a la página anterior sin incluir el nombre del controlador en la URL
            response.sendRedirect(request.getHeader("Referer"));
            return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
        } else if (action.equalsIgnoreCase("eliminar")){
            c_dao = new CarritoDAO();
            int id_carrito = Integer.parseInt(request.getParameter("id"));
            Carrito carrito = new Carrito();
            
            carrito.setId_carrito(id_carrito);
            c_dao.eliminar(id_carrito);
            c_dao.closeConnection();
            c_dao.closeResources();
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
