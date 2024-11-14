package com.example.horoscopochino.servlets;

import com.example.horoscopochino.dto.HoroscopoResponseDTO;
import com.example.horoscopochino.repositorio.HoroscopoMetodos;
import com.example.horoscopochino.servicio.HoroscopoServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarHoroscopoServlet", value = "/listarHoroscopo")
public class ListarHoroscopoServlet extends HttpServlet {
    private HoroscopoServicio horoscopoServicio;

    public void init() throws ServletException {
        this.horoscopoServicio = new HoroscopoServicio(new HoroscopoMetodos());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HoroscopoResponseDTO> horoscopos = horoscopoServicio.listarHoroscopos();
        request.setAttribute("horoscopos", horoscopos);
        request.getRequestDispatcher("/listaHoroscopo.jsp").forward(request, response);
    }
}