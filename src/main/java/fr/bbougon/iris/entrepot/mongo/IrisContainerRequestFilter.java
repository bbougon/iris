package fr.bbougon.iris.entrepot.mongo;

import fr.bbougon.iris.entrepot.Entrepots;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mongolink.MongoSession;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import java.io.IOException;

@PreMatching
public class IrisContainerRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        LOGGER.info("Request received");
        MongoSession session = MongoConfiguration.createSession();
        session.start();
        Entrepots.initialise(new EntrepotsMongos(session));
        LOGGER.info("Session started");
    }
    private static final Logger LOGGER = LogManager.getLogger(IrisContainerRequestFilter.class.getCanonicalName());
}
