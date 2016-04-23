package fr.bbougon.iris.web.ressources;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class MonApplication extends ResourceConfig {

    public MonApplication() {
        packages("fr.bbougon.iris.web.ressources");
    }

}
