package co.edu.unicundi.alumnoswar.controller;

import co.edu.unicundi.alumnosjar.service.IAlumnoService;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author acer
 */
@Path("/alumnos")
public class AlumnoController {
    
    @EJB
    private IAlumnoService service;
    
    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON) //Enviar la información en JSON
    public void insertar(){
        System.out.println("Excelente");
    }
}
