package fr.bbougon.iris.web.ressources;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class MonApplication extends ResourceConfig {

    public MonApplication() {
        register(JacksonFeature.class);
        packages(true, "fr.bbougon.iris.web.ressources");
        packages(true, "org.glassfish.jersey.examples.jackson");
    }

}
