/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDCARRITO;
import Modelo.Carrito;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAO implements CRUDCARRITO{

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listar(String id_usuario) {
        ArrayList<Carrito>list = new ArrayList<>(); 
        String sql = "select * from carrito where id_usuario = " + id_usuario;

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_carrito;
                int id_producto;
                int id_pedido;
                String comentario;
                
                while(rs.next()){
                        
                        id_carrito = rs.getInt("id_carrito");
                        id_producto = rs.getInt("id_producto");
                        id_pedido = rs.getInt("id_pedido");
                        comentario = rs.getString("comentario");
                        

                        Carrito carrito = new Carrito(id_carrito, id_producto, id_pedido, comentario);
                        list.add(carrito);
                }
        } catch(Exception e){
            System.out.println(e);
        } 
        return list;
    }
    
     @Override
    public List listarProducto(String id_usuario) {
        ArrayList<Carrito>list = new ArrayList<>(); 
        String sql = "select c.id_carrito, p.nombre_producto, p.descripcion_producto, p.precio  from carrito as c inner join producto as p on p.id_producto = c.id_producto inner join pedido as pe on pe.id_pedido = c.id_pedido where pe.id_usuario = " + id_usuario + " and pe.estado = 'iniciado';";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_carrito;
                String nombre_producto;
                String descripcion_producto;
                double precio;

                
                while(rs.next()){
                        
                        id_carrito = rs.getInt("id_carrito");
                        nombre_producto = rs.getString("nombre_producto");
                        descripcion_producto = rs.getString("descripcion_producto");
                        precio = rs.getDouble("precio");
                        

                        Carrito carrito = new Carrito();
                        carrito.setId_carrito(id_carrito);
                        carrito.setProducto(new Producto());
                        carrito.getProducto().setNombre_producto(nombre_producto);
                        carrito.getProducto().setDescripcion_producto(descripcion_producto);
                        carrito.getProducto().setPrecio(precio);
                        
                        list.add(carrito);
                }
        } catch(Exception e){
            System.out.println(e);
        } 

        return list;
    }

    @Override
    public Carrito list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Carrito carrito, int id_usuario) {
        try{
            
            String sql_pedido = "SELECT * FROM pedido where estado = 'iniciado' and id_usuario = " + id_usuario + " order by id_pedido desc limit 1";
            con = cn.getConnection();
            ps = con.prepareStatement(sql_pedido);
            rs = ps.executeQuery();
            
            int id_pedido = 0;
             if (rs.next()) {
                // Obtener los datos del resultado
                id_pedido = rs.getInt("id_pedido");

          
            } else {
                // No se encontraron resultados
                LocalDate fechaActual = LocalDate.now();
                 LocalTime horaActual = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String horaFormateada = horaActual.format(formatter);
                String sql_pedido_create = "INSERT INTO pedido(id_usuario , fecha , hora, estado, monto_pago, direccion) values("+id_usuario+", '"+fechaActual+"','"+horaFormateada+"', 'iniciado' ,0, '');";
                con = cn.getConnection();
                ps = con.prepareStatement(sql_pedido_create, Statement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
   
                if (rs.next()) {
                    // Obtener los datos del resultado
                    id_pedido = rs.getInt(1);
                }
             }
            
            int id_producto = carrito.getId_producto();
            String comentario = carrito.getComentario();
                
            String sql = "INSERT INTO carrito(id_producto, id_pedido, comentario) values("+id_producto+","+id_pedido+",'"+comentario+"');";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public boolean edit(Carrito carrito) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id_carrito) {
        try{
           

            String sql = "delete from carrito where id_carrito = "+id_carrito+";";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public List listarIdPedido(String id_pedido) {
       ArrayList<Carrito>list = new ArrayList<>(); 
        String sql = "select c.id_carrito, p.nombre_producto, p.descripcion_producto, p.precio, c.comentario  from carrito as c inner join producto as p on p.id_producto = c.id_producto inner join pedido as pe on pe.id_pedido = c.id_pedido where pe.id_pedido = " + id_pedido + ";";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_carrito_aux;
                String nombre_producto;
                String descripcion_producto;
                double precio;
                String comentario;

                
                while(rs.next()){
                        
                        id_carrito_aux = rs.getInt("id_carrito");
                        nombre_producto = rs.getString("nombre_producto");
                        descripcion_producto = rs.getString("descripcion_producto");
                        precio = rs.getDouble("precio");
                        comentario = rs.getString("comentario");
                        

                        Carrito carrito = new Carrito();
                        carrito.setId_carrito(id_carrito_aux);
                        carrito.setProducto(new Producto());
                        carrito.getProducto().setNombre_producto(nombre_producto);
                        carrito.getProducto().setDescripcion_producto(descripcion_producto);
                        carrito.getProducto().setPrecio(precio);
                        carrito.setComentario(comentario);
                        
                        list.add(carrito);
                }
        } catch(Exception e){
            System.out.println(e);
        } 

        return list;
    }
    
    public void closeResources() {
        // Cerrar el ResultSet
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Cerrar el PreparedStatement
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Cerrar la Connection
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        // Cerrar la conexión al final de todas las operaciones
        if (cn != null) {
            cn.closeConnection();
        }
    }

    @Override
    public boolean editComentario(Carrito carrito) {
        try {
          
            int id_carrito = carrito.getId_carrito();
            String comentario = carrito.getComentario();
            
            String sql = "update carrito set  comentario  = '"+comentario+"'  where id_carrito = '"+id_carrito+"';";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }
    
}
