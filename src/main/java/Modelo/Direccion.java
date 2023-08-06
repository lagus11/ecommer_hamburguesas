/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author lagus11
 */
public class Direccion {
    int id_direccion;
    int id_usuario;
    String comunidad;
    String numero_exterior;
   

    public Direccion(){
        
    }

    public Direccion(int id_usuario, String comunidad, String numero_exterior){
        this.id_usuario = id_usuario;
        this.comunidad = comunidad;
        this.numero_exterior = numero_exterior;
    }
    
    public Direccion(int id_direccion, int id_usuario, String comunidad, String numero_exterior){
        this.id_direccion = id_direccion;
        this.id_usuario = id_usuario;
        this.comunidad = comunidad;
        this.numero_exterior = numero_exterior;
    }
    
     public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getNumero_exterior() {
        return numero_exterior;
    }

    public void setNumero_exterior(String numero_exterior) {
        this.numero_exterior = numero_exterior;
    }
    
}
