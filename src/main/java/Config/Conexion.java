
package Config;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    Connection conn = null;
    public Conexion(){
	Statement stmt = null;
	ResultSet rs = null;


	// remoto servidor
	// String url = "jdbc:postgresql://node143408-prueba.jelastic.saveincloud.net:5432/prueba";
	// String user = "webadmin";
	// String password = "PVTqhc11667";

	// local - cambiar por su bd, usuario y contrasena
	String url = "jdbc:postgresql://localhost:5432/hamburguesas3";
	String user = "postgres";
	String password = "123";


	try {
            if(conn != null){
            } else {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(url, user, password);
            }  
               
    } catch (SQLException e) {
        System.out.print("error  " + e);
    }   catch (ClassNotFoundException ex) { 
            System.out.print("error 2 " + ex);
        } 
    
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void closeConnection() {
        // Método para cerrar la conexión
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Aquí podrías manejar el error adecuadamente según las necesidades de tu aplicación
            }
        }
    }
}
