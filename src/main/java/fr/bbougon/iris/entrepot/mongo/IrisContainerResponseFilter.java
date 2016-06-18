package fr.bbougon.iris.entrepot.mongo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

public class IrisContainerResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        LOGGER.info("Response sent");
        MongoConfiguration.flushAndStopSession();
        LOGGER.info("Session stopped");
    }
    private static final Logger LOGGER = LogManager.getLogger(IrisContainerResponseFilter.class.getCanonicalName());
}
