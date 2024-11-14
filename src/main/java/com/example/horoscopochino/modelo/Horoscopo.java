package com.example.horoscopochino.modelo;

import java.util.Date;

public class Horoscopo {
    private String animal;
    private Date fechaInicio;
    private Date fechaFin;

    public Horoscopo(String animal, Date fechaInicio, Date fechaFin) {
        this.animal = animal;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Horoscopo() {
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public java.sql.Date getFechaInicio() {
        return (java.sql.Date) fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public java.sql.Date getFechaFin() {
        return (java.sql.Date) fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
