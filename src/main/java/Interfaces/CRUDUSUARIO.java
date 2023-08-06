/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Usuario;
import java.util.List;

public interface CRUDUSUARIO {
    public List listar();
    public Usuario list(int id);
    public Usuario logueo(String email, String contrasena);
    public boolean add(Usuario usuario);
    public boolean edit(Usuario usuario);
    public boolean eliminar(int id);
}
