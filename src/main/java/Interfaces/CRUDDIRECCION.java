/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Direccion;
import java.util.List;


public interface CRUDDIRECCION {
    public List listar(String id_usuario);
    public Direccion list(int id);
    public boolean add(Direccion direccion);
    public boolean edit(Direccion direccion);
    public boolean eliminar(int id);
}
