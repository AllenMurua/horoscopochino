package com.example.horoscopochino.servicio;

import com.example.horoscopochino.dto.HoroscopoResponseDTO;
import com.example.horoscopochino.modelo.Horoscopo;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface HoroscopoServicioINT {
    List<HoroscopoResponseDTO> listarHoroscopos();
}
