package co.edu.unicundi.alumnoswar.controller;

import co.edu.unicundi.alumnosjar.entity.Alumno;
import co.edu.unicundi.alumnosjar.service.IAlumnoService;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author acer
 */
@Path("/alumnos")
public class AlumnoController {
    
    @EJB
    private IAlumnoService service;
    
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON) //Enviar la información en JSON
    public Response insertar(Alumno alumno){
        service.guardar(alumno);
        System.out.println("Registrado correctamente");
        return Response.status(Response.Status.CREATED).build();
    }
    
}
