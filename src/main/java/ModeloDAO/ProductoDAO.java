/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDPRODUCTO;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lagus11
 */
public class ProductoDAO implements CRUDPRODUCTO{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listar() {
        ArrayList<Producto>list = new ArrayList<>(); 
        String sql = "select * from producto";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_producto;
                int id_categoria;
                String nombre_producto;
                String descripcion_producto;
                double precio;
                String estado;
                String imgUrl;

                while(rs.next()){
                        id_producto = rs.getInt("id_producto");
                        id_categoria = rs.getInt("id_categoria");
                        nombre_producto = rs.getString("nombre_producto");
                        descripcion_producto = rs.getString("descripcion_producto");
                        precio = rs.getInt("precio");
                        estado = rs.getString("estado");
                        imgUrl = rs.getString("imgUrl");

                        Producto product = new Producto(id_producto, id_categoria, nombre_producto, descripcion_producto, precio, estado, imgUrl);
                        list.add(product);
                }
        } catch(Exception e){
            System.out.println(e);
        } 

        return list;
    }
    @Override
    public List listarLimit() {
        return listarLimit(3);
    }
    
 
    public List listarLimit(int limite) {
        ArrayList<Producto>list = new ArrayList<>(); 
        String sql = "select * from producto limit " + limite;

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_producto;
                int id_categoria;
                String nombre_producto;
                String descripcion_producto;
                double precio;
                String estado;
                String imgUrl;

                while(rs.next()){
                        id_producto = rs.getInt("id_producto");
                        id_categoria = rs.getInt("id_categoria");
                        nombre_producto = rs.getString("nombre_producto");
                        descripcion_producto = rs.getString("descripcion_producto");
                        precio = rs.getInt("precio");
                        estado = rs.getString("estado");
                        imgUrl = rs.getString("imgUrl");

                        Producto product = new Producto(id_producto, id_categoria, nombre_producto, descripcion_producto, precio, estado, imgUrl);
                        list.add(product);
                }
        } catch(Exception e){
            System.out.println(e);
        } 

        return list;
    }
    
    @Override
    public List listarCategoria(String categoria) {
         ArrayList<Producto>list = new ArrayList<>(); 
        String sql = "select * from producto as p inner join categoria as c on c.id_categoria = p.id_categoria where c.nombre_categoria =  '" + categoria +"';";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_producto;
                int id_categoria;
                String nombre_producto;
                String descripcion_producto;
                double precio;
                String estado;
                String imgUrl;

                while(rs.next()){
                        id_producto = rs.getInt("id_producto");
                        id_categoria = rs.getInt("id_categoria");
                        nombre_producto = rs.getString("nombre_producto");
                        descripcion_producto = rs.getString("descripcion_producto");
                        precio = rs.getInt("precio");
                        estado = rs.getString("estado");
                        imgUrl = rs.getString("imgurl");

                        Producto product = new Producto(id_producto, id_categoria, nombre_producto, descripcion_producto, precio, estado, imgUrl);
                        list.add(product);
                }
        } catch(Exception e){
            System.out.println(e);
        } 

        return list;
    }
    
  

    @Override
    public List list(String id) {
     
        ArrayList<Producto>list = new ArrayList<>(); 
        String sql = "select * from producto where id_producto =  '" + id +"';";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_producto;
                int id_categoria;
                String nombre_producto;
                String descripcion_producto;
                double precio;
                String estado;
                String imgUrl; 

                while(rs.next()){
                        id_producto = rs.getInt("id_producto");
                        id_categoria = rs.getInt("id_categoria");
                        nombre_producto = rs.getString("nombre_producto");
                        descripcion_producto = rs.getString("descripcion_producto");
                        precio = rs.getInt("precio");
                        estado = rs.getString("estado");
                        imgUrl = rs.getString("imgUrl");

                        Producto product = new Producto(id_producto, id_categoria, nombre_producto, descripcion_producto, precio, estado, imgUrl);
                        list.add(product);
                }
        } catch(Exception e){
            System.out.println(e);
        }

        return list;
    }

    @Override
    public String add(Producto product) {
        try{
            int id_categoria = product.getId_categoria();
            String nombre_producto = product.getNombre_producto();
            String descripcion_producto = product.getDescripcion_producto();
            double precio = product.getPrecio();
            String estado = product.getEstado();
            String imgUrl = product.getImgUrl();

            
            String sql = "INSERT INTO producto(id_categoria, nombre_producto,descripcion_producto,precio,estado, imgUrl) values("+id_categoria+",'"+nombre_producto+"','"+descripcion_producto+"',"+precio+",'"+estado+"', '');";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
            String sql_ultimate_id = "SELECT id_producto FROM producto order by id_producto desc limit 1 ";
            con = cn.getConnection();
            ps = con.prepareStatement(sql_ultimate_id);
            rs = ps.executeQuery();
            
            int count_id = 0;
             if (rs.next()) {
                // Obtener los datos del resultado
                count_id = rs.getInt("id_producto");
            }
      
            //separo el formato de la imagen
            String[] formatoImg = imgUrl.split("\\.");
            
            String nueva_url_img = (count_id) +"." + formatoImg[1];
            String update = "update producto set imgUrl = '"+nueva_url_img+"' where id_producto = '"+count_id+"';";
            con = cn.getConnection();
            ps = con.prepareStatement(update);
            ps.executeUpdate();
            
            return nueva_url_img;
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return "";
    }

    @Override
    public boolean edit(Producto product) {
        try{
            int id_producto = product.getId();
            String nombre_producto = product.getNombre_producto();
            String descripcion_producto = product.getDescripcion_producto();
            double precio = product.getPrecio();
            String estado = product.getEstado();
            //String imgUrl = product.getImgUrl();

            String sql = "update producto set  nombre_producto = '"+nombre_producto+"' , descripcion_producto = '"+descripcion_producto+"', precio = '"+precio+"', estado = '"+estado+"' where id_producto = '"+id_producto+"';";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public boolean eliminar(int id_producto) {
         try{
           

            String sql = "delete from producto where id_producto = "+id_producto+";";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
         
        return false;
    }

    @Override
    public Producto listProducto(int id) {
        ArrayList<Producto>list = new ArrayList<>(); 
        String sql = "select * from producto where id_producto =  '" + id +"';";
        Producto product = null;
        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_producto;
                int id_categoria;
                String nombre_producto;
                String descripcion_producto;
                double precio;
                String estado;
                String imgUrl; 
                
                while(rs.next()){
                        id_producto = rs.getInt("id_producto");
                        id_categoria = rs.getInt("id_categoria");
                        nombre_producto = rs.getString("nombre_producto");
                        descripcion_producto = rs.getString("descripcion_producto");
                        precio = rs.getInt("precio");
                        estado = rs.getString("estado");
                        imgUrl = rs.getString("imgUrl");

                        product = new Producto(id_producto, id_categoria, nombre_producto, descripcion_producto, precio, estado, imgUrl);
                        list.add(product);
                }
        } catch(Exception e){
            System.out.println(e);
        } 
        
        return product;
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
}
