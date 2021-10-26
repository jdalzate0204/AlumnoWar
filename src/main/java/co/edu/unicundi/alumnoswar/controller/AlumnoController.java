package co.edu.unicundi.alumnoswar.controller;

import co.edu.unicundi.alumnosjar.entity.Alumno;
import co.edu.unicundi.alumnosjar.service.IAlumnoService;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.validation.ConstraintViolation;
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

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertar(Alumno alumno) {
        service.guardar(alumno);
        System.out.println("Registrado correctamente");
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(Alumno alumno) throws CloneNotSupportedException{
        HashMap<String, String> errores = new HashMap();

        for (ConstraintViolation error: alumno.validar())
            errores.put(error.getPropertyPath().toString(), error.getMessage());

        if (errores.size() > 0)
            throw new IllegalArgumentException(errores.toString());
        else {
            service.editar(alumno);
            return Response.status(Response.Status.OK).build();
        }
    }
}
