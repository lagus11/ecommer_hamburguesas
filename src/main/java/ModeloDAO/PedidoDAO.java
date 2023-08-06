/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDPEDIDO;
import Modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PedidoDAO implements CRUDPEDIDO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    

    @Override
    public List listar() {
        ArrayList<Pedido>list = new ArrayList<>(); 
        String sql = "select * from pedido order by id_pedido asc";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_pedido ;
                int id_usuario_aux;
                int id_tarjeta;
                String fecha;
                String hora;
                String estado;
                String direccion;
                String monto_pago;
                
                while(rs.next()){
                        id_pedido = rs.getInt("id_pedido");
                        id_usuario_aux = rs.getInt("id_usuario");
                        id_tarjeta = rs.getInt("id_tarjeta");
                        fecha = rs.getString("fecha");
                        hora = rs.getString("hora");
                        estado = rs.getString("estado");
                        direccion = rs.getString("direccion");
                        monto_pago = rs.getString("monto_pago");

                        Pedido pedido = new Pedido(id_pedido, id_usuario_aux, id_tarjeta, fecha, hora, estado, direccion,monto_pago);
                        list.add(pedido);
                }
        } catch(Exception e){
            System.out.println(e);
        }

        return list;
    }

    @Override
    public List listar(String id_usuario) {
        ArrayList<Pedido>list = new ArrayList<>(); 
        String sql = "select * from pedido where id_usuario = " + id_usuario + " order by id_pedido asc";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_pedido ;
                int id_usuario_aux;
                int id_tarjeta;
                String fecha;
                String hora;
                String estado;
                String direccion;
                String monto_pago;
                
                while(rs.next()){
                        id_pedido = rs.getInt("id_pedido");
                        id_usuario_aux = rs.getInt("id_usuario");
                        id_tarjeta = rs.getInt("id_tarjeta");
                        fecha = rs.getString("fecha");
                        hora = rs.getString("hora");
                        estado = rs.getString("estado");
                        direccion = rs.getString("direccion");
                        monto_pago = rs.getString("monto_pago");

                        Pedido pedido = new Pedido(id_pedido, id_usuario_aux, id_tarjeta, fecha, hora, estado, direccion,monto_pago);
                        list.add(pedido);
                }
        } catch(Exception e){
            System.out.println(e);
        }

        return list;
    }

    @Override
    public boolean add(Pedido pedido) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean edit(Pedido pedido) {
        try{
            
            String sql_pedido = "SELECT * FROM pedido where estado = 'iniciado' and id_usuario = " + pedido.getId_usuario() + " order by id_pedido desc limit 1";
            con = cn.getConnection();
            ps = con.prepareStatement(sql_pedido);
            rs = ps.executeQuery();
            
            int id_pedido = 0;
             if (rs.next()) {
                // Obtener los datos del resultado
                id_pedido = rs.getInt("id_pedido");

          
            } else {
                // No se encontraron resultados
                String sql_pedido_create = "INSERT INTO pedido(id_usuario , fecha , hora, estado, monto_pago) values("+pedido.getId_usuario()+", '06/18/23','19:30', 'iniciado' ,60);";
                con = cn.getConnection();
                ps = con.prepareStatement(sql_pedido_create, Statement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
   
                if (rs.next()) {
                    // Obtener los datos del resultado
                    id_pedido = rs.getInt(1);
                }
             }
            
 
            String sql = "UPDATE pedido set estado = '"+pedido.getEstado()+"', id_tarjeta = " + pedido.getId_tarjeta() + ", direccion = '"+ pedido.getDireccion() +"' ,monto_pago = "+pedido.getMonto_pago()+" where id_pedido = " + id_pedido;

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Pedido list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean editEstado(Pedido pedido) {
        try {
          
            int id_pedido = pedido.getId_pedido();
            String estado = pedido.getEstado();
            String hora = pedido.getHora();
            

            String sql = "update pedido set  estado  = '"+estado+"' , hora  = '"+hora+"' where id_pedido = '"+id_pedido+"';";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
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
