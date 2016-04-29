package fr.bbougon.iris.fr.bbougon.iris.web.utilitaires;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class IrisExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception exception) {
        return Response.status(BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.APPLICATION_JSON).build();
    }

}
