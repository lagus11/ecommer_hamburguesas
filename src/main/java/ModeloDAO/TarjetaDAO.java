/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDTARJETA;
import Modelo.Tarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TarjetaDAO implements CRUDTARJETA{

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listar(String id_usuario) {
        ArrayList<Tarjeta>list = new ArrayList<>(); 
        String sql = "select * from tarjeta where id_usuario = " + id_usuario;

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_tarjeta;
                int id_usuario_aux;
                String numero;
                String fecha_caducidad;
                String titular;
                String emisor;

                while(rs.next()){
                        id_tarjeta = rs.getInt("id_tarjeta");
                        id_usuario_aux = rs.getInt("id_usuario");
                        numero = rs.getString("numero");
                        fecha_caducidad = rs.getString("fecha_caducidad");
                        titular = rs.getString("titular");
                        emisor = rs.getString("emisor");

                        Tarjeta direccion = new Tarjeta(id_tarjeta, id_usuario_aux, numero, fecha_caducidad, titular, emisor);
                        list.add(direccion);
                }
        } catch(Exception e){
            System.out.println(e);
        } 

        return list;
    }

    @Override
    public Tarjeta list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Tarjeta tarjeta) {
        try{
            int id_usuario = tarjeta.getId_usuario();
            String numero = tarjeta.getNumero();
            String fecha_caducidad = tarjeta.getFecha_caducidad();
            String titular = tarjeta.getTitular();
            String emisor = tarjeta.getEmisor();

            String sql = "INSERT INTO tarjeta(id_usuario, numero, fecha_caducidad, titular, emisor) values("+id_usuario+",'"+numero+"','"+fecha_caducidad+"', '"+titular+"', '"+emisor+"');";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public boolean edit(Tarjeta tarjeta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
