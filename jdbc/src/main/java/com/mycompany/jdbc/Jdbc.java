/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.jdbc;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class Jdbc {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "David Suarez";
    static final String PASS = "1234";
    static final String QUERY = "SELECT * FROM videojuegos";

    public static void main(String[] args)  {
// Abre la conexión
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(QUERY);) {
            while (rs.next()) {
                System.out.print("  ID: " + rs.getInt("id"));
                System.out.print(", Nombre: " + rs.getString("Nombre"));
                System.out.print(", Genero: " + rs.getString("Genero"));
                System.out.print(", Precio: " + rs.getFloat("Precio"));
                System.out.print(", Compañia: " + rs.getString("Compañia"));
                System.out.print(", Fecha Lanzamiento" + rs.getDate("FechaLanzamiento"));
                
                System.out.println("");

            }
             Scanner teclado = new Scanner(System.in);
            System.out.println("\nIngresar un nuevo videojuego:");

            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();

            System.out.print("Genero: ");
            String genero = teclado.nextLine();

            System.out.print("Precio: ");
            float precio = teclado.nextFloat();

            System.out.print("Compañia: ");
            String compañia = teclado.nextLine();

           // System.out.print("Fecha Lanzamiento (YYYY-MM-DD): ");
           // String fechaLanzamiento = teclado.next();

            // Insertar el nuevo videojuego
            try (PreparedStatement insertStmt = conn.prepareStatement("INSERT_QUERY")) {
                insertStmt.setString(2, nombre);
                insertStmt.setString(3, genero);
                insertStmt.setFloat(4, precio);
                insertStmt.setString(5, compañia);
                insertStmt.setDate(6, Date.valueOf("2003-10-10"));
                int rowsAffected = insertStmt.executeUpdate();
                System.out.println(rowsAffected + " fila(s) afectada(s). Nuevo videojuego insertado con éxito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
