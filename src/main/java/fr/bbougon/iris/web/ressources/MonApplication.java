package fr.bbougon.iris.web.ressources;

import fr.bbougon.iris.entrepot.mongo.IrisContainerRequestFilter;
import fr.bbougon.iris.entrepot.mongo.IrisContainerResponseFilter;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.IrisExceptionMapper;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.IrisMapperProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class MonApplication extends ResourceConfig {

    public MonApplication() {
        register(JacksonFeature.class);
        register(IrisMapperProvider.class);
        register(IrisExceptionMapper.class);
        register(IrisContainerRequestFilter.class);
        register(IrisContainerResponseFilter.class);
        packages(true, "fr.bbougon.iris.web.ressources");
        packages(true, "org.glassfish.jersey.examples.jackson");
    }

}
