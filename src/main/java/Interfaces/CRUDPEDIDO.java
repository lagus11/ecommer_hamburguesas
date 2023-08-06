/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Pedido;
import java.util.List;


public interface CRUDPEDIDO {
    public List listar();
    public List listar(String id_usuario);
    public Pedido list(int id);
    public boolean add(Pedido pedido);
    public boolean edit(Pedido pedido);
    public boolean editEstado(Pedido pedido);
    public boolean eliminar(int id);
}
