package com.example.horoscopochino.dto;

import java.util.Date;

public class UsuarioObtenerDTO { private String nombre;
    private String username;
    private String password;
    private String email;
    private Date fechaNacimiento;
    private String animal;


    public UsuarioObtenerDTO() {}

    public UsuarioObtenerDTO(String nombre, String username, String password, String email, Date fechaNacimiento, String animal) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "UsuarioObtenerDTO{" +
                "nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", animal='" + animal + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
