package com.example.horoscopochino.modelo;

import java.util.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String username;
    private String email;
    private Date fechaNacimiento;  // java.util.Date
    private String password;
    private String animal;

    public Usuario() {}

    public Usuario(String nombre, String username, String email, Date fechaNacimiento, String password, String animal) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.password = password;
        this.animal = animal;
    }

    // Getter y Setter para el atributo fechaNacimiento
    public java.sql.Date getFechaNacimientoSql() {
        // Convertimos la fechaNacimiento a java.sql.Date solo en este punto
        return (fechaNacimiento != null) ? new java.sql.Date(fechaNacimiento.getTime()) : null;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", password='" + password + '\'' +
                ", animal='" + animal + '\'' +
                '}';
    }
}
