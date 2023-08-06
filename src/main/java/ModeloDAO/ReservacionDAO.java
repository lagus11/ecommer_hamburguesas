/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDRESERVACION;
import Modelo.Reservacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReservacionDAO implements CRUDRESERVACION{

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listar(String id_usuario) {
        ArrayList<Reservacion>list = new ArrayList<>(); 
        String sql = "select * from reservacion where id_usuario = " + id_usuario;

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_reservacion;
                int id_usuario_aux;
                String nombre_reservacion;
                String fecha_reservacion;
                String hora;
                String direccion;
                String telefono;

                while(rs.next()){
                        id_reservacion = rs.getInt("id_reservacion");
                        id_usuario_aux = rs.getInt("id_usuario");
                        nombre_reservacion = rs.getString("nombre_reservacion");
                        fecha_reservacion = rs.getString("fecha_reservacion");
                        hora = rs.getString("hora");
                        direccion = rs.getString("direccion");
                        telefono = rs.getString("telefono");

                        Reservacion reservacion = new Reservacion(id_reservacion, id_usuario_aux, nombre_reservacion, fecha_reservacion, hora, direccion, telefono);
                        list.add(reservacion);
                }
        } catch(Exception e){
            System.out.println(e);
        } 

        return list;
    }

    @Override
    public Reservacion list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Reservacion reservacion) {
        try{
            int id_reservacion = reservacion.getId_reservacion();
            int id_usuario = reservacion.getId_usuario();
            String nombre_reservacion = reservacion.getNombre_reservacion();
            String fecha_reservacion = reservacion.getFecha_reservacion();
            String hora = reservacion.getHora();
            String direccion = reservacion.getDireccion();
            String telefono = reservacion.getTelefono();

            String sql = "INSERT INTO reservacion(id_usuario, nombre_reservacion, fecha_reservacion, hora, direccion, telefono) values("+id_usuario+",'"+nombre_reservacion+"','"+fecha_reservacion+"', '"+hora+"', '"+direccion+"' , '"+telefono+"');";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public boolean edit(Reservacion reservacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id_reservacion) {
        try{
            String sql = "delete from reservacion where id_reservacion = "+id_reservacion+";";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public List listar() {
        ArrayList<Reservacion>list = new ArrayList<>(); 
        String sql = "select * from reservacion";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_reservacion;
                int id_usuario_aux;
                String nombre_reservacion;
                String fecha_reservacion;
                String hora;
                String direccion;
                String telefono;

                while(rs.next()){
                        id_reservacion = rs.getInt("id_reservacion");
                        id_usuario_aux = rs.getInt("id_usuario");
                        nombre_reservacion = rs.getString("nombre_reservacion");
                        fecha_reservacion = rs.getString("fecha_reservacion");
                        hora = rs.getString("hora");
                        direccion = rs.getString("direccion");
                        telefono = rs.getString("telefono");

                        Reservacion reservacion = new Reservacion(id_reservacion, id_usuario_aux, nombre_reservacion, fecha_reservacion, hora, direccion, telefono);
                        list.add(reservacion);
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
    
}
