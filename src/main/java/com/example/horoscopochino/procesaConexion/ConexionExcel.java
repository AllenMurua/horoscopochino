package com.example.horoscopochino.procesaConexion;

import com.example.horoscopochino.procesaConexion.ConexionDB;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionExcel {

    public void cargarDatosDesdeExcel(String rutaArchivo) {
        String sql = "INSERT INTO horoscopo (animal, fecha_inicio, fecha_fin) VALUES (?, ?, ?)";

        try (FileInputStream archivoExcel = new FileInputStream(rutaArchivo);
             Workbook workbook = new XSSFWorkbook(archivoExcel);
             Connection connection = ConexionDB.getInstance().getConnection()) {

            Sheet hoja = workbook.getSheetAt(0);
            for (Row fila : hoja) {
                if (fila.getRowNum() == 0) continue; // Saltar encabezado

                String animal = fila.getCell(0).getStringCellValue();
                java.util.Date fechaInicio = fila.getCell(1).getDateCellValue();
                java.util.Date fechaFin = fila.getCell(2).getDateCellValue();

                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, animal);
                    ps.setDate(2, new java.sql.Date(fechaInicio.getTime()));
                    ps.setDate(3, new java.sql.Date(fechaFin.getTime()));
                    ps.executeUpdate();
                }
            }
            System.out.println("Datos cargados exitosamente desde el archivo Excel.");

        } catch (IOException e) {
            System.err.println("Error al leer el archivo Excel: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al cargar los datos en la base de datos: " + e.getMessage());
        }
    }
}
