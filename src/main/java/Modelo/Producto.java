/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author lagus11
 */
public class Producto {
    int id_producto;
    int id_categoria;
    String nombre_producto;
    String descripcion_producto;
    double precio;
    String estado;
    String imgUrl;

    public Producto(){
        
    }
    
    public Producto(int id_producto, int id_categoria, String nombre_producto, String descripcion_producto, double precio, String estado, String imgUrl){
        this.id_producto = id_producto;
        this.id_categoria = id_categoria;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.precio = precio;
        this.estado = estado;
        this.imgUrl = imgUrl;
    }
    
    public Producto(int id_categoria, String nombre_producto, String descripcion_producto, double precio, String estado, String imgUrl){
        this.id_categoria = id_categoria;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.precio = precio;
        this.estado = estado;
        this.imgUrl = imgUrl;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setId(int id){
            this.id_producto = id;
    }
    
    public int getId(){
            return id_producto;
    }


    public void setId_categoria(int id_categoria){
            this.id_categoria = id_categoria;
    }
    
    public int getId_categoria(){
            return id_categoria;
    }
    
    public void setNombre_producto(String nombre_producto){
            this.nombre_producto = nombre_producto;
    }
    
    public String getNombre_producto(){
            return nombre_producto;
    }
    
    public void setDescripcion_producto(String descripcion_producto){
            this.descripcion_producto = descripcion_producto;
    }
    
    public String getDescripcion_producto(){
            return descripcion_producto;
    }
    
    public void setPrecio(double precio){
            this.precio = precio;
    }
    
    public double getPrecio(){
            return precio;
    }
    
    public void setEstado(String estado){
            this.estado = estado;
    }
    
    public String getEstado(){
            return estado;
    }
}
