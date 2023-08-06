/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDCATEGORIA;
import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO implements CRUDCATEGORIA {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    @Override
    public List listar() {
        ArrayList<Categoria>list = new ArrayList<>(); 
        String sql = "select * from categoria";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_categoria;
                String nombre_categoria;
                String descripcion;
                String estado;

                while(rs.next()){
                        id_categoria = rs.getInt("id_categoria");
                        nombre_categoria = rs.getString("nombre_categoria");
                        descripcion = rs.getString("descripcion");
                        estado = rs.getString("estado");
                  
                        Categoria categoria = new Categoria(id_categoria, nombre_categoria, descripcion, estado);
                        list.add(categoria);
                }
        } catch(Exception e){
            System.out.println(e);
        }

        return list;
    }

    @Override
    public List list(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Categoria product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean edit(Categoria product) {
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
