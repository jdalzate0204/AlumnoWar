package co.edu.unicundi.alumnoswar.exception;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author acer
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Context private UriInfo uriInfo; 
    
    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        ExceptionWraper ew;
        
        if (exception instanceof IllegalArgumentException) { //400
            ew = new ExceptionWraper(Response.Status.BAD_REQUEST.getStatusCode(), 
                                      Response.Status.BAD_REQUEST.getReasonPhrase(), 
                                      exception.getMessage(), 
                                      uriInfo.getPath());
            return Response.status(Response.Status.BAD_REQUEST).entity(ew).build();  
        } 
        else if (exception instanceof NotFoundException | exception instanceof IllegalStateException) { //404
            ew = new ExceptionWraper(Response.Status.NOT_FOUND.getStatusCode(), 
                                      Response.Status.NOT_FOUND.getReasonPhrase(), 
                                      exception.getMessage(),
                                      uriInfo.getPath());
            return Response.status(Response.Status.NOT_FOUND).entity(ew).build();
        } 
        else if (exception instanceof NotAllowedException) { //405
            ew = new ExceptionWraper(Response.Status.METHOD_NOT_ALLOWED.getStatusCode(), 
                                      Response.Status.METHOD_NOT_ALLOWED.getReasonPhrase(), 
                                      "Operación de petición incorrecta", 
                                      uriInfo.getPath());
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(ew).build();
        } 
        else if (exception instanceof CloneNotSupportedException) { //409
            ew = new ExceptionWraper(Response.Status.CONFLICT.getStatusCode(), 
                                      Response.Status.CONFLICT.getReasonPhrase(), 
                                      exception.getMessage(), 
                                      uriInfo.getPath());
            return Response.status(Response.Status.CONFLICT).entity(ew).build();
        } 
        else if (exception instanceof WebApplicationException) { //415
            ew = new ExceptionWraper(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), 
                                      Response.Status.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(), 
                                      "Formato mal formado", 
                                      uriInfo.getPath());
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(ew).build();
        }
        else { //500
            ew = new ExceptionWraper(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 
                                      Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
                                      "Error interno", 
                                      uriInfo.getPath());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ew).build();
        }
    }
}
