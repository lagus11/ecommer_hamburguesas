/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Reservacion;
import java.util.List;

public interface CRUDRESERVACION {
    public List listar(String id_usuario);
    public List listar();
    public Reservacion list(int id);
    public boolean add(Reservacion reservacion);
    public boolean edit(Reservacion reservacion);
    public boolean eliminar(int id);
}
