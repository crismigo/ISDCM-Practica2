/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcm.restservice.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author alumne
 */
public class Usuario {
    private static Connection getConnection() {
        String usuarioDB = "carlacris";
        String pwdDB = "carlacris";
        String url = "jdbc:derby://localhost:1527/webApp1DB";
  
        try {
            return DriverManager.getConnection(url, usuarioDB, pwdDB);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Usuario() {}
    
    public boolean verificarUsuario(String username, String password) {
        // Aquí debes conectar con la base de datos y comprobar la existencia del usuario y la validez de la contraseña
        // Este es solo un ejemplo básico, asumiendo que la conexión a la base de datos ya está configurada
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Usuario WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return true; // Usuario encontrado y contraseña válida
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no se encuentra el usuario o la contraseña es incorrecta
    }
    
    public boolean registrarUsuario(String name, String surname, String email, String username, String password) {
        String query = "INSERT INTO Usuario (name, surname, email, username, password) VALUES (?, ?, ?, ?, ?)";
        Connection conn = getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, email);
            stmt.setString(4, username);
            stmt.setString(5, password);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Si se insertaron filas, el registro fue exitoso
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
