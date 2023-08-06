/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Carrito;
import Modelo.Direccion;
import Modelo.Pedido;
import ModeloDAO.CarritoDAO;
import ModeloDAO.PedidoDAO;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // Tamaño máximo de archivo antes de guardar en disco (1 MB)
                 maxFileSize = 1024 * 1024 * 5, // Tamaño máximo permitido para el archivo (5 MB)
                 maxRequestSize = 1024 * 1024 * 10) // Tamaño total máximo de la solicitud (10 MB)
public class ControladorPedido extends HttpServlet {

    PedidoDAO p_dao;
    CarritoDAO c_dao;
    String VistaPedido = "vistas/pedido.jsp";
    String VistaAdminPedidos = "vistas/adminPedidos.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorPedido</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorPedido at " + request.getContextPath() + "</h1>");
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
            p_dao = new PedidoDAO();
            int id_usuario = Integer.parseInt(request.getSession().getAttribute("id") + "");
            String monto_pago  = request.getParameter("monto_pago");
            String direccion = request.getParameter("direccion");
          
            String efectivo = request.getParameter("efectivo");
            int id_tarjeta = 0;
            if("true".equals(efectivo)){
            }else{
                String id_tarjeta_aux = request.getParameter("id_tarjeta");
                
                if(id_tarjeta_aux != null && !id_tarjeta_aux.isEmpty()){
                    id_tarjeta = Integer.parseInt(id_tarjeta_aux);
                }
            }
            Pedido pedido = new Pedido();
            pedido.setId_usuario(id_usuario);
            pedido.setEstado("solicitado");
            pedido.setId_tarjeta(id_tarjeta);
            pedido.setMonto_pago(monto_pago);
            pedido.setDireccion(direccion);
            
            p_dao.edit(pedido); 
            p_dao.closeConnection();
            p_dao.closeResources();
            
            //agrego los comentarios en el carrito
            c_dao = new CarritoDAO();
           
            // Obtener el JSON del array "comentariosData" enviado en el FormData
            String comentariosDataJson = request.getParameter("comentariosData");
            
            // Convertir el JSON a una lista de objetos manualmente
            List<JsonObject> comentariosData = new ArrayList<>();
            
            try (JsonReader jsonReader = Json.createReader(new StringReader(comentariosDataJson))) {
                JsonArray jsonArray = jsonReader.readArray();
                for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                    comentariosData.add(jsonObject);
                }
            }
            
             // Ahora tienes la lista de objetos "comentariosData" y otros parámetros,
            // puedes hacer lo que necesites con ellos, por ejemplo, procesar los comentarios
            for (JsonObject comentario : comentariosData) {
                String id_carrito = comentario.getString("id_carrito");
                String comentarioTexto = comentario.getString("comentario");

                Carrito carrito = new Carrito();
                carrito.setId_carrito(Integer.parseInt(id_carrito));
                carrito.setComentario(comentarioTexto);
                c_dao.editComentario(carrito);
            }
            
            c_dao.closeConnection();
            c_dao.closeResources();
            acceso = VistaPedido;
        } else if (action.equalsIgnoreCase("Actualizar")){
                p_dao = new PedidoDAO();
                int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
                String estado = request.getParameter("estado");
                String hora = request.getParameter("hora");
                
                Pedido pedido = new Pedido();
                pedido.setId_pedido(id_pedido);
                pedido.setEstado(estado);
                pedido.setHora(hora);
                
                boolean result = p_dao.editEstado(pedido); 
                p_dao.closeConnection();
                p_dao.closeResources();
                acceso = VistaAdminPedidos;
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
