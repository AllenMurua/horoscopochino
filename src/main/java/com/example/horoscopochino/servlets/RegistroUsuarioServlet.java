package com.example.horoscopochino.servlets;

import com.example.horoscopochino.dto.HoroscopoResponseDTO;
import com.example.horoscopochino.dto.UsuarioCrearDTO;
import com.example.horoscopochino.repositorio.HoroscopoMetodos;
import com.example.horoscopochino.servicio.HoroscopoServicio;
import com.example.horoscopochino.servicio.UsuarioServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "RegistroUsuarioServlet", value = "/registrarUsuario")
public class RegistroUsuarioServlet extends HttpServlet {
    private UsuarioServicio usuarioServicio;
    private HoroscopoServicio horoscopoServicio;  // Se mantiene esta variable

    @Override
    public void init() throws ServletException {
        // Inicialización de usuarioServicio
        usuarioServicio = new UsuarioServicio(new HoroscopoMetodos());

        // Inicialización de horoscopoServicio con un objeto de tipo HoroscopoMetodos
        HoroscopoMetodos metodosHoroscopo = new HoroscopoMetodos(); // Asegúrate de que esta clase esté correctamente implementada
        horoscopoServicio = new HoroscopoServicio(metodosHoroscopo);  // Aquí inicializas correctamente HoroscopoServicio
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");  // Cambia a String
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Verificar si todos los campos necesarios están presentes
        if (nombre == null || username == null || email == null || fechaNacimientoStr == null || password == null || confirmPassword == null || nombre.isEmpty() || username.isEmpty() || email.isEmpty() || fechaNacimientoStr.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("mensaje", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // Verificar si las contraseñas coinciden
        if (!password.equals(confirmPassword)) {
            request.setAttribute("mensaje", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // Convertir fechaNacimientoStr de String a Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = sdf.parse(fechaNacimientoStr);  // Convierte la fecha en Date
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al procesar la fecha de nacimiento.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // Determinar el animal según la fecha de nacimiento
        String animal = setAnimalFechaNac(fechaNacimiento);  // Ahora pasamos el Date
        System.out.println(animal);
        // Crear el DTO
        UsuarioCrearDTO usuarioDTO = new UsuarioCrearDTO(nombre, username, password, email, fechaNacimiento, animal);
        System.out.println(usuarioDTO);
        // Crear el usuario
        usuarioServicio.crearUsuario(usuarioDTO);

        // Redirigir con mensaje de éxito
        request.setAttribute("mensaje", "Registro exitoso. Por favor, inicia sesión.");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


    private String setAnimalFechaNac(Date fechaNacimiento) {
        List<HoroscopoResponseDTO> listaHoroscopos = horoscopoServicio.listarHoroscopos();

        LocalDate fechaNacimientoLocal = new java.sql.Date(fechaNacimiento.getTime()).toLocalDate();  // Convertir Date a LocalDate

        for (HoroscopoResponseDTO horoscopo : listaHoroscopos) {
            if ((fechaNacimientoLocal.isEqual(horoscopo.getFechaInicio()) || fechaNacimientoLocal.isAfter(horoscopo.getFechaInicio())) &&
                    (fechaNacimientoLocal.isEqual(horoscopo.getFechaFinal()) || fechaNacimientoLocal.isBefore(horoscopo.getFechaFinal()))) {
                return horoscopo.getAnimal();
            }
        }

        return "Animal no encontrado";
    }


}
