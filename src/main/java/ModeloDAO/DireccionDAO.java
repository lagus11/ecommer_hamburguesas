/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDDIRECCION;
import Modelo.Direccion;
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
public class DireccionDAO implements CRUDDIRECCION{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    @Override
    public List listar(String id_usuario) {
        ArrayList<Direccion>list = new ArrayList<>(); 
        String sql = "select * from direccion where id_usuario = " + id_usuario;

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_direccion;
                int id_usuario_aux;
                String comunidad;
                String numero_exterior;

                while(rs.next()){
                        id_direccion = rs.getInt("id_direccion");
                        id_usuario_aux = rs.getInt("id_usuario");
                        comunidad = rs.getString("comunidad");
                        numero_exterior = rs.getString("numero_exterior");

                        Direccion direccion = new Direccion(id_direccion, id_usuario_aux, comunidad, numero_exterior);
                        list.add(direccion);
                }
        } catch(Exception e){
            System.out.println(e);
        } 

        return list;
    }

    @Override
    public Direccion list(int id) {
        ArrayList<Direccion>list = new ArrayList<>(); 
        String sql = "select * from direccion where id_direccion =  '" + id +"';";
        Direccion direccion = null;
        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_direccion;
                int id_usuario;
                String comunidad;
                String numero_exterior;
                
                while(rs.next()){
                        id_direccion = rs.getInt("id_direccion");
                        id_usuario = rs.getInt("id_usuario");
                        comunidad = rs.getString("comunidad");
                        numero_exterior = rs.getString("numero_exterior");
             

                        direccion = new Direccion(id_direccion, id_usuario, comunidad, numero_exterior);
                        list.add(direccion);
                }
        } catch(Exception e){
            System.out.println(e);
        } 
        
        return direccion;
    }

    @Override
    public boolean add(Direccion direccion) {
        try{
            int id_usuario = direccion.getId_usuario();
            String comunidad = direccion.getComunidad();
            String numero_exterior = direccion.getNumero_exterior();

            String sql = "INSERT INTO direccion(id_usuario, comunidad, numero_exterior) values("+id_usuario+",'"+comunidad+"','"+numero_exterior+"');";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public boolean edit(Direccion direccion) {
        try{
            int id_direccion = direccion.getId_direccion();
            String comunidad = direccion.getComunidad();
            String numero_exterior = direccion.getNumero_exterior();
            

            String sql = "update direccion set  comunidad  = '"+comunidad+"' , numero_exterior  = '"+numero_exterior+"' where id_direccion = '"+id_direccion+"';";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public boolean eliminar(int id_direccion) {
        try{
           

            String sql = "delete from direccion where id_direccion = "+id_direccion+";";

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
