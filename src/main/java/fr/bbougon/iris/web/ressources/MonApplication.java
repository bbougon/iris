package fr.bbougon.iris.web.ressources;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class MonApplication extends ResourceConfig {

    public MonApplication() {
        register(JacksonFeature.class);
        packages("fr.bbougon.iris.web.ressources");
        packages("org.glassfish.jersey.examples.jackson");
    }

}
