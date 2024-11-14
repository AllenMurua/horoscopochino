package com.example.horoscopochino.servlets;

import com.example.horoscopochino.dto.UsuarioObtenerDTO;
import com.example.horoscopochino.repositorio.HoroscopoMetodos;
import com.example.horoscopochino.servicio.UsuarioServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ServletInicioSesion", value = "/login")
public class InicioSesionServlet extends HttpServlet {
    private UsuarioServicio usuarioServicio;

    @Override
    public void init() throws ServletException {
        usuarioServicio = new UsuarioServicio(new HoroscopoMetodos());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("username");
        String pass = request.getParameter("password");

        // Usar ambos parámetros en la llamada
        UsuarioObtenerDTO usuarioAutenticado = usuarioServicio.obtenerUsuario(nombreUsuario, pass);

        System.out.println("Usuario autenticado: " + usuarioAutenticado);

        if (usuarioAutenticado != null && usuarioAutenticado.getUsername() != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioAutenticado);

            if ("admin".equals(usuarioAutenticado.getUsername())) {
                response.sendRedirect("AdminDashboard.jsp");
            } else {
                response.sendRedirect("ConsultadeHoroscopo.jsp");
            }
        } else {
            request.setAttribute("error", "Nombre de usuario o contraseña incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}