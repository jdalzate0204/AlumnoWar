package co.edu.unicundi.alumnoswar.controller;

import co.edu.unicundi.alumnosjar.entity.Alumno;
import co.edu.unicundi.alumnosjar.service.IAlumnoService;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author acer
 */
@Path("/alumnos")
public class AlumnoController {
    
    @EJB
    private IAlumnoService service;
    
    @GET
    @Path("/obtener/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) {
        Alumno alumno = service.listarPorId(id);
        return Response.status(Response.Status.OK).entity(alumno).build();
    }
    
    @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtener(){
        List<Alumno> listaAlumno=service.listar();
        return Response.status(Response.Status.OK).entity(listaAlumno).build();
    }

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON) //Enviar la información en JSON
    public Response insertar(Alumno alumno) throws CloneNotSupportedException{
        service.guardar(alumno);
        System.out.println("Registrado correctamente");
        return Response.status(Response.Status.CREATED).build();
    }
}
