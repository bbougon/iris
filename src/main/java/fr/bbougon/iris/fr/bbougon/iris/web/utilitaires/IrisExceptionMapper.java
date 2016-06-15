package fr.bbougon.iris.fr.bbougon.iris.web.utilitaires;

import com.google.gson.Gson;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class IrisExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception exception) {
        int status = BAD_REQUEST.getStatusCode();
        Object entity = exception.getMessage();
        if (exception instanceof WebApplicationException) {
            status = ((WebApplicationException) exception).getResponse().getStatus();
            entity = ((WebApplicationException) exception).getResponse().getEntity();
        }
        return Response.status(status)
                .entity(new Gson().toJson(entity))
                .type(MediaType.APPLICATION_JSON).build();
    }

}
