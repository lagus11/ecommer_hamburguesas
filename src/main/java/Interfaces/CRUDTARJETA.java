/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Tarjeta;
import java.util.List;


public interface CRUDTARJETA {
    public List listar(String id_usuario);
    public Tarjeta list(int id);
    public boolean add(Tarjeta tarjeta);
    public boolean edit(Tarjeta tarjeta);
    public boolean eliminar(int id);
}
