/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class Carrito {
    int id_carrito;
    int id_producto;
    int id_pedido ;
    String comentario;
    Producto producto;

    public Carrito(){
        
    }

    public Carrito(int id_carrito, int id_producto, int id_pedido ,String comentario){
        this.id_carrito = id_carrito;
        this.id_producto = id_producto;
        this.id_pedido = id_pedido;
        this.comentario = comentario;
    }
    
    public Carrito(int id_producto,int id_pedido, String comentario){
        this.id_producto = id_producto;
        this.id_pedido = id_pedido;
        this.comentario = comentario;
    }

    public int getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
