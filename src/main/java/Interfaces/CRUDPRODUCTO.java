/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Producto;
import java.util.List;


public interface CRUDPRODUCTO {
    public List listar();
    public List listarLimit();
    public List listarCategoria(String categoria);
    public List list(String id);
    public Producto listProducto(int id);
    public String add(Producto product);
    public boolean edit(Producto product);
    public boolean eliminar(int id);
}
