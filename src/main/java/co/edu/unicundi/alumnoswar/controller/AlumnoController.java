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
        List<Alumno> listaAlumno = service.listar();
        return Response.status(Response.Status.OK).entity(listaAlumno).build();
    }

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertar(Alumno alumno) throws CloneNotSupportedException{
        service.guardar(alumno);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(Alumno alumno) throws CloneNotSupportedException{
        service.editar(alumno);
        return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) {
          service.eliminar(id);
          return Response.status(Response.Status.NO_CONTENT).build();
    } 
}
