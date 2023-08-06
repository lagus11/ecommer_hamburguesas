/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author lagus11
 */
public class Reservacion {
    int id_reservacion;
    int id_usuario;
    String nombre_reservacion;
    String fecha_reservacion;
    String hora;
    String direccion;
    String telefono;
   

    public Reservacion(){
        
    }

    public Reservacion(int id_reservacion, int id_usuario, String nombre_reservacion, String fecha_reservacion, String hora, String direccion, String telefono){
        this.id_reservacion = id_reservacion;
        this.id_usuario = id_usuario;
        this.nombre_reservacion = nombre_reservacion;
        this.fecha_reservacion = fecha_reservacion;
        this.hora = hora;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public Reservacion(int id_usuario, String nombre_reservacion, String fecha_reservacion, String hora, String direccion, String telefono){
        this.id_usuario = id_usuario;
        this.nombre_reservacion = nombre_reservacion;
        this.fecha_reservacion = fecha_reservacion;
        this.hora = hora;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_reservacion() {
        return nombre_reservacion;
    }

    public void setNombre_reservacion(String nombre_reservacion) {
        this.nombre_reservacion = nombre_reservacion;
    }

    public String getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(String fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
