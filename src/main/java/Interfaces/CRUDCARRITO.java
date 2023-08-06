/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Carrito;
import java.util.List;


public interface CRUDCARRITO {
    public List listar(String id_usuario);
    public List listarProducto(String id_usuario);
    public List listarIdPedido(String id_pedido);
    public Carrito list(int id);
    public boolean add(Carrito carrito, int id_usuario);
    public boolean edit(Carrito carrito);
    public boolean editComentario(Carrito carrito);
    public boolean eliminar(int id);
}
