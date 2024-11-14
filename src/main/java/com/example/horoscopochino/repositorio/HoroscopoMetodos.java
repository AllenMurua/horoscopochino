package com.example.horoscopochino.repositorio;

import com.example.horoscopochino.modelo.Horoscopo;
import com.example.horoscopochino.modelo.Usuario;
import com.example.horoscopochino.procesaConexion.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoroscopoMetodos implements HoroscopoMetodosIns {

    @Override
    public List<Horoscopo> obtenerHoroscopo() {
        List<Horoscopo> listaHoroscopo = new ArrayList<>();
        String sql = "SELECT animal, fecha_inicio, fecha_fin FROM horoscopo";

        try (Connection conn = ConexionDB.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Horoscopo h = new Horoscopo();
                h.setAnimal(rs.getString("animal"));
                h.setFechaInicio(rs.getDate("fecha_inicio"));
                h.setFechaFin(rs.getDate("fecha_fin"));
                listaHoroscopo.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los horóscopos de la BD: " + e.getMessage());
        }
        return listaHoroscopo;
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, username, email, fecha_nacimiento, password, animal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getEmail());
            ps.setDate(4, usuario.getFechaNacimientoSql()); // Usa getFechaNacimientoSql() para convertir
            ps.setString(5, usuario.getPassword());
            ps.setString(6, usuario.getAnimal());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar usuario: " + e.getMessage());
        }
    }


    @Override
    public Usuario InicioSesion(String username, String password) {
        String sql = "SELECT nombre, username, email, fecha_nacimiento, password, animal FROM usuarios WHERE username = ? AND password = ?";
        Usuario usuarioLogeado = new Usuario();

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    // Verificar cada campo antes de asignarlo
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    String animal = rs.getString("animal");
                    Date fechaNacimiento = rs.getDate("fecha_nacimiento");

                    if (nombre != null) usuarioLogeado.setNombre(nombre);
                    usuarioLogeado.setUsername(rs.getString("username"));
                    usuarioLogeado.setPassword(rs.getString("password"));
                    if (email != null) usuarioLogeado.setEmail(email);
                    if (animal != null) usuarioLogeado.setAnimal(animal);
                    if (fechaNacimiento != null) usuarioLogeado.setFechaNacimiento(fechaNacimiento);

                    // Agregar logging para depuración
                    System.out.println("Datos recuperados de BD: " +
                            "nombre=" + nombre +
                            ", email=" + email +
                            ", animal=" + animal +
                            ", fechaNacimiento=" + fechaNacimiento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al iniciar sesión en la BD: " + e.getMessage());
        }
        return usuarioLogeado;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, username = ?, email = ?, fecha_nacimiento = ?, password = ? WHERE id = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getEmail());
            ps.setDate(4, usuario.getFechaNacimientoSql()); // Usa getFechaNacimientoSql() para convertir
            ps.setString(5, usuario.getPassword());

            ps.executeUpdate();
            System.out.println("Usuario actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar usuario en la BD: " + e.getMessage());
        }
    }

    @Override
    public void borrarUsuario(Usuario usuario) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuario.getId()); // Asegúrate de que el usuario tenga un ID válido

            ps.executeUpdate();
            System.out.println("Usuario eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar usuario de la BD: " + e.getMessage());
        }

    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT nombre, username, email, fecha_nacimiento, password, animal FROM usuarios";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setNombre(rs.getString("nombre"));
                usuario.setUsername(rs.getString("username"));
                usuario.setEmail(rs.getString("email"));
                usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setPassword(rs.getString("password"));
                usuario.setAnimal(rs.getString("animal"));

                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar usuarios de la BD: " + e.getMessage());
        }

        return listaUsuarios;
    }
}