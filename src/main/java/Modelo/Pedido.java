/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class Pedido {
    int id_pedido ;
    int id_usuario;
    int id_tarjeta;
    String fecha;
    String hora;
    String estado;
    String direccion;
    String monto_pago;

    public Pedido(){
        
    }

    public Pedido(int id_pedido, int id_usuario, int id_tarjeta ,String fecha, String hora, String estado, String direccion, String monto_pago){
        this.id_pedido = id_pedido;
        this.id_usuario = id_usuario;
        this.id_tarjeta = id_tarjeta;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.direccion = direccion;
        this.monto_pago = monto_pago;
    }
    
    public Pedido(int id_usuario, int id_tarjeta ,String fecha, String hora, String estado, String direccion, String monto_pago){
        this.id_usuario = id_usuario;
        this.id_tarjeta = id_tarjeta;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.direccion = direccion;
        this.monto_pago = monto_pago;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(String monto_pago) {
        this.monto_pago = monto_pago;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
