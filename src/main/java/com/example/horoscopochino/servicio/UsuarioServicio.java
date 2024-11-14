
package com.example.horoscopochino.servicio;

import com.example.horoscopochino.dto.UsuarioCrearDTO;
import com.example.horoscopochino.dto.UsuarioObtenerDTO;
import com.example.horoscopochino.modelo.Usuario;
import com.example.horoscopochino.repositorio.HoroscopoMetodos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioServicio implements UsuarioServicioINT {
    private final HoroscopoMetodos metodosHoroscopo;

    public UsuarioServicio(HoroscopoMetodos metodosHoroscopo) {
        this.metodosHoroscopo = metodosHoroscopo;
    }

    private UsuarioObtenerDTO mapToDTO(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioObtenerDTO(
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getFechaNacimiento(),
                usuario.getAnimal()
        );
    }

    private Usuario mapToEntity(UsuarioCrearDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setAnimal(dto.getAnimal());
        return usuario;
    }

    @Override
    public List<UsuarioObtenerDTO> listarUsuarios() {
        return metodosHoroscopo.listarUsuarios().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioObtenerDTO> buscarUsuario(String username) {
        return metodosHoroscopo.listarUsuarios().stream()
                .filter(usuario -> usuario.getUsername() != null && usuario.getUsername().equals(username))
                .map(this::mapToDTO)
                .findFirst();
    }

    @Override
    public UsuarioObtenerDTO obtenerUsuario(String username, String password) {
        return buscarUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
    }

    @Override
    public void crearUsuario(UsuarioCrearDTO usuarioCreateDTO) {
        Usuario usuario = mapToEntity(usuarioCreateDTO);
        metodosHoroscopo.insertarUsuario(usuario);
    }

    @Override
    public void actualizarUsuario(UsuarioObtenerDTO usuarioObtenerDTO) {
        Optional<Usuario> usuarioOptional = metodosHoroscopo.listarUsuarios().stream()
                .filter(u -> u.getUsername() == usuarioObtenerDTO.getUsername())
                .findFirst();

        Usuario usuario = usuarioOptional.orElseThrow(() -> new RuntimeException("Usuario no encontrado para actualizar: " + usuarioObtenerDTO.getUsername()));
        usuario.setUsername(usuarioObtenerDTO.getUsername());
        usuario.setPassword(usuarioObtenerDTO.getPassword());

        metodosHoroscopo.actualizarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(int id) {
        Optional<Usuario> usuarioOptional = metodosHoroscopo.listarUsuarios().stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        Usuario usuario = usuarioOptional.orElseThrow(() -> new RuntimeException("Usuario no encontrado para eliminar: " + id));
        metodosHoroscopo.borrarUsuario(usuario);
    }
}