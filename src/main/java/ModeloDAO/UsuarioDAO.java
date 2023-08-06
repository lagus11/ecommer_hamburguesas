/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDUSUARIO;
import Modelo.Usuario;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author lagus11
 */
public class UsuarioDAO implements CRUDUSUARIO{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String claveSecreta = "MiClaveSecreta1234"; // Debes cambiar esto por tu propia clave
 

    
    @Override
    public List listar() {
         ArrayList<Usuario>list = new ArrayList<>(); 
        String sql = "select * from usuario";

        try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                int id_usuario;
                String nombre_usuario;
                String apellido;
                String telefono;
                String email;

                while(rs.next()){
                        id_usuario = rs.getInt("id_usuario");
                        nombre_usuario = rs.getString("nombre_usuario");
                        apellido = rs.getString("apellido");
                        telefono = rs.getString("telefono");
                        email = rs.getString("email");

                        Usuario usuario = new Usuario();
                        usuario.setId_usuario(id_usuario);
                        usuario.setNombre_usuario(nombre_usuario);
                        usuario.setApellido(apellido);
                        usuario.setTelefono(telefono);
                        usuario.setEmail(email);
                                
                        list.add(usuario);
                }
        } catch(Exception e){
            System.out.println(e);
        } 

        return list;
    }

    @Override
    public Usuario list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Usuario usuario) {
        
        try{
           
            String contrasenaEncriptada = encriptar(usuario.getContrasena());

            String sql = "INSERT INTO usuario(nombre_usuario, apellido, telefono, email, contraseña) " +
            "values ('"+usuario.getNombre_usuario()+"','"+usuario.getApellido()+"','"+usuario.getTelefono()+"', '"+usuario.getEmail()+"', '"+contrasenaEncriptada+"');";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return false;
    }

    @Override
    public boolean edit(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private String encriptar(String contrasena) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(claveSecreta.toCharArray(), "salt".getBytes(), 65536, 128);
        SecretKeySpec secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] datosEncriptados = cipher.doFinal(contrasena.getBytes());
        return Base64.getEncoder().encodeToString(datosEncriptados);
    }
    
     public static String desencriptar(String textoEncriptado) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(claveSecreta.toCharArray(), "salt".getBytes(), 65536, 128);
        SecretKeySpec secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] datosDesencriptados = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));
        return new String(datosDesencriptados);
    }

    @Override
    public Usuario logueo(String email, String contrasena) {
        try{
           
            String contrasenaEncriptada = encriptar(contrasena);

            String sql = "select u.id_usuario as id_usuario, r.tipo as tipo_rol from usuario as u left join usuario_rol as ur on ur.id_usuario = u.id_usuario left join rol as r on ur.id_rol = r.id_rol where u.email = '" + email + "' and u.contraseña = '" + contrasenaEncriptada +"';";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            Usuario usuario = new Usuario();
            if (rs.next()) {
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setRol(rs.getString("tipo_rol"));
            } else {
                return usuario;
            }
            return usuario;
        }catch(Exception e){
            System.out.println(e);
        } 
        
        return null;
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
