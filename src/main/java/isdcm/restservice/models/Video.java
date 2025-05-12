/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcm.restservice.models;

import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author alumne
 */
public class Video {
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
    private int id;
    private String title;
    private String author;
    private String description;
    private String creationdate;
    private String duration;
    private int streams;
    private String format;
    private String filepath;
    
    public Video() {
        
    }
    
    private Video(int id, String title, String author, String description, 
            String creationdate, String duration, int streams, String format, 
            String filepath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.creationdate = creationdate;
        this.duration = duration;
        this.streams = streams;
        this.format = format;
        this.filepath = filepath;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getCreationdate() {
        return this.creationdate;
    }
    
    public String getDuration() {
        return this.duration;
    }
    
    public Integer getStreams() {
        return this.streams;
    }
    
    public String getFormat() {
        return this.format;
    }
    
    public String getFilepath() {
        return this.filepath;
    }
    
    public String getFilename() {
        if (filepath == null) return null;
        String[] parts = filepath.split("/");
        return parts[parts.length - 1];
    }
    
    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStreams(int streams) {
        this.streams = streams;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    
    public static List<Video> getAll() {
        List<Video> videos = new ArrayList<>();
        try {
            String query = "SELECT * FROM Video";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Video video = new Video(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("description"),
                    rs.getString("creationdate"),
                    rs.getString("duration"),
                    rs.getInt("streams"),
                    rs.getString("format"),
                    rs.getString("filePath")
                );
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }
   
    public static List<Video> searchByTitle(String title) {
        List<Video> videos = new ArrayList<>();
        try {
            String query = "SELECT * FROM Video where title LIKE ?";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + title + "%"); 
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Video video = new Video(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("description"),
                    rs.getString("creationdate"),
                    rs.getString("duration"),
                    rs.getInt("streams"),
                    rs.getString("format"),
                    rs.getString("filePath")
                );
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }
    
    public static List<Video> searchByAuthor(String author) {
        List<Video> videos = new ArrayList<>();
        try {
            String query = "SELECT * FROM Video where author LIKE ?";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + author + "%"); 
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Video video = new Video(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("description"),
                    rs.getString("creationdate"),
                    rs.getString("duration"),
                    rs.getInt("streams"),
                    rs.getString("format"),
                    rs.getString("filePath")
                );
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }
    
    public static List<Video> searchByYear(String year) {
        List<Video> videos = new ArrayList<>();
        try {
            String query = "SELECT * FROM Video where creationdate LIKE ?";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, year + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Video video = new Video(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("description"),
                    rs.getString("creationdate"),
                    rs.getString("duration"),
                    rs.getInt("streams"),
                    rs.getString("format"),
                    rs.getString("filePath")
                );
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }
    
    public static List<Video> searchByMonth(String month) {
        List<Video> videos = new ArrayList<>();
        try {
            if (month.length() == 1) month = "0" + month;
            
            String query = "SELECT * FROM Video WHERE MONTH(creationDate) = ?";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, month);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Video video = new Video(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("description"),
                    rs.getString("creationdate"),
                    rs.getString("duration"),
                    rs.getInt("streams"),
                    rs.getString("format"),
                    rs.getString("filePath")
                );
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }
    
    public static List<Video> searchByDay(String day) {
        List<Video> videos = new ArrayList<>();
        try {
            if (day.length() == 1 && day.matches("\\d")) day = "0" + day;
            String query = "SELECT * FROM Video WHERE DAY(creationDate) = ?";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, day);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Video video = new Video(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("description"),
                    rs.getString("creationdate"),
                    rs.getString("duration"),
                    rs.getInt("streams"),
                    rs.getString("format"),
                    rs.getString("filePath")
                );
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }

    public static boolean updateViews(Integer id){
        try {
            String query = "UPDATE Video SET streams = streams + 1 WHERE id = ?";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
            } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean registrarVideo(String title, String author, String description, String duration, String filepath, String format) {
        String query = "INSERT INTO Video (title, author, description, creationDate, streams, duration, format, filePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = getConnection();
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String creationDate = fechaHoraActual.format(formatter);
        Integer streams = 0;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, description);
            stmt.setString(4, creationDate);
            stmt.setInt(5, streams);
            stmt.setString(6, duration);
            stmt.setString(7, format);
            stmt.setString(8, filepath);
            System.out.println(stmt);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Si se insertaron filas, el registro fue exitoso
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } 
}


