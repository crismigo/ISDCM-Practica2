/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcm.restservice.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import isdcm.restservice.models.Video;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Path("/videos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideoService {
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

    private static final String DB_URL = "jdbc:derby://localhost:1527/webApp1DB";
    private static final String DB_USER = "carlacris";
    private static final String DB_PASSWORD = "carlacris";

    @PUT
    @Path("/views")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("*/*")
    public Response updateViews(@QueryParam("id") int id) {
        boolean updated = Video.updateViews(id);
        if (updated) {
            String json = "{\"success\":true, \"message\":\"Reproducción actualizada\"}";
            return Response.ok(json).build();
        } else {
            String json = "{\"success\":false, \"message\":\"Video no encontrado o error\"}";
            return Response.status(Response.Status.NOT_FOUND).entity(json).build();
        }
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerVideo(Video video) {
        System.out.print("register");
        boolean inserted = Video.registrarVideo(video.getTitle(), video.getAuthor(), video.getDescription(), video.getDuration(), video.getFilepath(), video.getFormat());
        if (inserted) {
            String json = "{\"success\":true, \"message\":\"Video registrado correctamente\"}";
            return Response.ok(json).build();
        } else {
            String json = "{\"success\":false, \"message\":\"Error al registrar el video\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("")
    public Response searchAll() {
        System.out.print("all");
        List<Video> videos = Video.getAll();
        return Response.ok(videos).build();
    }

    // Búsqueda por título
    @GET
    @Path("/title/{title}")
    public Response searchByTitle(@PathParam("title") String title) {
        title = URLDecoder.decode(title, StandardCharsets.UTF_8);
        List<Video> videos = Video.searchByTitle(title);
        return Response.ok(videos).build();
    }

    // Búsqueda por autor
    @GET
    @Path("/author/{author}")
    public Response searchByAuthor(@PathParam("author") String author) {
        author = URLDecoder.decode(author, StandardCharsets.UTF_8);
        List<Video> videos = Video.searchByAuthor(author);
        return Response.ok(videos).build();
    }

    // Búsqueda por año
    @GET
    @Path("/year/{year}")
    public Response searchByYear(@PathParam("year") String year) {
        List<Video> videos = Video.searchByYear(year);
        return Response.ok(videos).build();
    }
    
    // Búsqueda por mes
    @GET
    @Path("/month/{month}")
    public Response searchByMonth(@PathParam("month") String month) {
        List<Video> videos = Video.searchByMonth(month);
        return Response.ok(videos).build();
    }
    
    // Búsqueda por dia
    @GET
    @Path("/day/{day}")
    public Response searchByDay(@PathParam("day") String day) {
        System.out.print("diaaaaaaaaa");
        List<Video> videos = Video.searchByDay(day);
        return Response.ok(videos).build();
    }
}
