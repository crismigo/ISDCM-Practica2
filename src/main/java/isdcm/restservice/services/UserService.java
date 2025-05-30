/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcm.restservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import isdcm.restservice.models.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.util.Date;

@Path("login")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    @POST
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        System.out.println("login");
        Usuario usuario = new Usuario();
        if (usuario.verificarUsuario(username, password)) {
            String jwt = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 hora
                .signWith(SignatureAlgorithm.HS512, "claveSecreta123")
                .compact();

            JsonObject json = Json.createObjectBuilder()
                .add("JWT", jwt)
                .build();

            return Response.ok(json.toString()).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                           .entity("Usuario o contraseña incorrectos")
                           .build();
        }
    }
}
