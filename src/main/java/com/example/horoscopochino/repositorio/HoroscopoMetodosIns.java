package com.example.horoscopochino.repositorio;

import com.example.horoscopochino.modelo.Horoscopo;
import com.example.horoscopochino.modelo.Usuario;

import java.util.List;

public interface HoroscopoMetodosIns {
    List<Horoscopo> obtenerHoroscopo();
    void insertarUsuario(Usuario usuario);
    Usuario InicioSesion(String username, String password);
    void actualizarUsuario(Usuario usuario);
    void borrarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();

}
