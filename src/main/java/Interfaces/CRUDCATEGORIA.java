/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Categoria;
import java.util.List;


public interface CRUDCATEGORIA {
    public List listar();
    public List list(String id);
    public boolean add(Categoria product);
    public boolean edit(Categoria product);
    public boolean eliminar(int id);
}
