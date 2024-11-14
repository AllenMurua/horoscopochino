
import com.example.horoscopochino.repositorio.HoroscopoMetodos;
import com.example.horoscopochino.modelo.Usuario;
import com.example.horoscopochino.procesaConexion.ConexionDB;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class HoroscopoTest {

    public static void main(String[] args) {
        // Consulta SQL para obtener todos los registros de la tabla 'horoscopo'
        String sql = "SELECT animal, fecha_inicio, fecha_fin FROM horoscopo";
        String sql2 = "SELECT * FROM usuario";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int count = 0;
            while (rs.next()) {
                String animal = rs.getString("animal");
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_fin");

                System.out.println("Registro " + (++count) + ":");
                System.out.println("  Animal: " + animal);
                System.out.println("  Fecha de inicio: " + fechaInicio);
                System.out.println("  Fecha de fin: " + fechaFin);
                System.out.println("------------------------");
            }

            if (count > 0) {
                System.out.println("Total de registros en la tabla 'horoscopo': " + count);
            } else {
                System.out.println("No existen datos en la tabla 'horoscopo'.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al consultar la tabla 'horoscopo': " + e.getMessage());
        }
    }

    @Test
    void usuarioTest() {
        HoroscopoMetodos horoscopoMetodos = new HoroscopoMetodos();

        List<Usuario> usuarios = horoscopoMetodos.listarUsuarios();

        // Verificar si la lista de usuarios está vacía o contiene datos
        if (usuarios.isEmpty()) {
            System.out.println("No se encontraron usuarios en la base de datos.");
        } else {
            System.out.println("Usuarios en la base de datos:");

            // Iterar sobre la lista de usuarios e imprimir sus datos
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId());
                System.out.println("Nombre: " + usuario.getNombre());
                System.out.println("Username: " + usuario.getUsername());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("Fecha de Nacimiento: " + usuario.getFechaNacimiento());
                System.out.println("Animal: " + usuario.getAnimal());
                System.out.println("--------------------------");
            }
        }
    }
}