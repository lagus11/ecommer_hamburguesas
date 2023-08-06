/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import ModeloDAO.ProductoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // Tamaño máximo de archivo antes de guardar en disco (1 MB)
                 maxFileSize = 1024 * 1024 * 5, // Tamaño máximo permitido para el archivo (5 MB)
                 maxRequestSize = 1024 * 1024 * 10) // Tamaño total máximo de la solicitud (10 MB)
public class ControladorProducto extends HttpServlet {

    String index = "index.jsp";
    String menu = "vistas/menu.jsp";
    String agregarProducto = "vistas/agregarProducto.jsp";
    String editarProducto = "vistas/editarProducto.jsp";
    String adminProducto = "vistas/adminProductos.jsp";

    ProductoDAO p_dao;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorProducto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorProducto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acceso = "";
        String action = request.getParameter("accion"); //variable para guardar que accion quiero hacer
        HttpSession session = request.getSession();
        if(action.equalsIgnoreCase("listar")){
           acceso = index; 
        } else if(action.equalsIgnoreCase("listarCategoria")){
           acceso = menu; 
        } else if (action.equalsIgnoreCase("eliminar")){
            p_dao = new ProductoDAO();
            int id_producto = Integer.parseInt(request.getParameter("id"));
            Producto producto = new Producto();
            
            producto.setId_producto(id_producto);
            p_dao.eliminar(id_producto);
            p_dao.closeConnection();
            p_dao.closeResources();
            // Redirigir a la página anterior sin incluir el nombre del controlador en la URL
            response.sendRedirect(request.getHeader("Referer"));
            return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
        } else if (action.equalsIgnoreCase("editar")){
                session = request.getSession();
                session.setAttribute("id_producto", request.getParameter("id"));
                acceso = editarProducto;
        }
        
        response.sendRedirect(acceso);
        return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String acceso = "";
            String action = request.getParameter("accion"); //variable para guardar que accion quiero hacer
            
            if (action.equalsIgnoreCase("Agregar")){
                p_dao = new ProductoDAO();
                String nombre_producto = request.getParameter("nombre_producto") ;
                int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
                String descripcion_producto = request.getParameter("descripcion_producto");
                double precio = Integer.parseInt(request.getParameter("precio"));
                String estado = request.getParameter("estado");
                
            
                Part imgUrl = request.getPart("imgUrl");
                String nombreImagen = imgUrl.getSubmittedFileName();
                
                String rutaImagenes =  getServletContext().getRealPath("/");
            
                Producto producto = new Producto(id_categoria, nombre_producto, descripcion_producto, precio, estado, nombreImagen);
                String nueva_url_img = p_dao.add(producto); 
                p_dao.closeConnection();
                p_dao.closeResources();
                // Guardar la imagen en el servidor
                try (InputStream inputStream = imgUrl.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(new File(rutaImagenes + "/imagenes/productos/" + nueva_url_img))) {
                    int bytesRead;
                    byte[] buffer = new byte[4096];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                acceso = adminProducto;
                // Redirigir a la página anterior sin incluir el nombre del controlador en la URL
                //response.sendRedirect(request.getHeader("Referer"));
                //return; // Importante: Agregar esta línea para evitar cualquier otro procesamiento después de la redirección
            } else if (action.equalsIgnoreCase("Actualizar")){
                p_dao = new ProductoDAO();
                String nombre_producto = request.getParameter("nombre_producto") ;
                String descripcion_producto = request.getParameter("descripcion_producto");
                double precio = Double.parseDouble(request.getParameter("precio"));
                String estado = request.getParameter("estado");
                //String imgUrl = request.getParameter("imgUrl");

                Producto producto = new Producto();
                producto.setId(Integer.parseInt((String)request.getSession().getAttribute("id_producto")));
                producto.setNombre_producto(nombre_producto);
                producto.setDescripcion_producto(descripcion_producto);
                producto.setPrecio(precio);
                producto.setEstado(estado);
                //producto.setImgUrl(imgUrl);
                p_dao.edit(producto);
                p_dao.closeConnection();
                p_dao.closeResources();
                acceso = adminProducto;
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
