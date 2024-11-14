package com.example.horoscopochino.servlets;

import com.example.horoscopochino.dto.UsuarioObtenerDTO;
import com.example.horoscopochino.repositorio.HoroscopoMetodos;
import com.example.horoscopochino.servicio.UsuarioServicio;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarUsuariosServlet", urlPatterns = {"/listarUsuarios"})
public class ListarUsuariosServlet extends HttpServlet {
    private UsuarioServicio usuarioServicio;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.usuarioServicio = new UsuarioServicio(new HoroscopoMetodos());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UsuarioObtenerDTO> usuarios = usuarioServicio.listarUsuarios();
        req.setAttribute("usuarios", usuarios);
        req.getRequestDispatcher("ConsultaUsuarios.jsp").forward(req, resp);
    }
}