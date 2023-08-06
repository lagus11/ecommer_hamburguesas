/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author lagus11
 */
public class Tarjeta {
    int id_tarjeta;
    int id_usuario;
    String numero;
    String fecha_caducidad;
    String titular;
    String emisor;
   

    public Tarjeta(){
        
    }

    public Tarjeta(int id_tarjeta, int id_usuario, String numero, String fecha_caducidad, String titular, String emisor){
        this.id_tarjeta = id_tarjeta;
        this.id_usuario = id_usuario;
        this.numero = numero;
        this.fecha_caducidad = fecha_caducidad;
        this.titular = titular;
        this.emisor = emisor;
    }
    
    public Tarjeta( int id_usuario, String numero, String fecha_caducidad, String titular, String emisor){
        this.id_usuario = id_usuario;
        this.numero = numero;
        this.fecha_caducidad = fecha_caducidad;
        this.titular = titular;
        this.emisor = emisor;
    }

    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(String fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }
    
    
    
}
