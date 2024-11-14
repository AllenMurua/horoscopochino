package com.example.horoscopochino.servicio;

import com.example.horoscopochino.dto.HoroscopoResponseDTO;
import com.example.horoscopochino.modelo.Horoscopo;
import com.example.horoscopochino.repositorio.HoroscopoMetodos;

import java.util.List;
import java.util.stream.Collectors;

public class HoroscopoServicio implements HoroscopoServicioINT {
    private HoroscopoMetodos metodos;

    public HoroscopoServicio(HoroscopoMetodos metodosHoroscopo) {
        this.metodos = metodosHoroscopo;
    }

    @Override
    public List<HoroscopoResponseDTO> listarHoroscopos() {
        return metodos.obtenerHoroscopo().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private HoroscopoResponseDTO mapToDTO(Horoscopo horoscopo) {
        HoroscopoResponseDTO dto = new HoroscopoResponseDTO();
        dto.setFechaFinal(horoscopo.getFechaFin());
        dto.setAnimal(horoscopo.getAnimal());
        dto.setFechaInicio(horoscopo.getFechaInicio());
        return dto;
    }
}
