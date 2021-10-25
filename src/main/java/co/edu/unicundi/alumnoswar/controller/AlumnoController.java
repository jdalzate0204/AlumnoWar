package co.edu.unicundi.alumnoswar.controller;

import co.edu.unicundi.alumnosjar.service.IAlumnoService;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 *
 * @author acer
 */
@Path("/alumnos")
public class AlumnoController {
    
    @EJB
    private IAlumnoService service;
}
