package com.example.horoscopochino.dto;

import java.sql.Date;
import java.time.chrono.ChronoLocalDate;

public class HoroscopoResponseDTO {

    private String animal;
    private Date fechaInicio;
    private Date fechaFinal;

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public ChronoLocalDate getFechaInicio() {
        return fechaInicio.toLocalDate();
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ChronoLocalDate getFechaFinal() {
        return fechaFinal.toLocalDate();
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
