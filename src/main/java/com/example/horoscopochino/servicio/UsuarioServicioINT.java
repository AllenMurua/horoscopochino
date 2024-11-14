package com.example.horoscopochino.servicio;

import com.example.horoscopochino.dto.UsuarioCrearDTO;
import com.example.horoscopochino.dto.UsuarioObtenerDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicioINT {
    List<UsuarioObtenerDTO> listarUsuarios();
    Optional<UsuarioObtenerDTO> buscarUsuario(String username);
    UsuarioObtenerDTO obtenerUsuario(String usrnm, String password);
    void crearUsuario(UsuarioCrearDTO usuarioCreateDTO);
    void actualizarUsuario(UsuarioObtenerDTO usuarioObtenerDTO);
    void eliminarUsuario(int id);
}
