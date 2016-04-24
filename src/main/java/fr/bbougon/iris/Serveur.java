package fr.bbougon.iris;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.InetSocketAddress;

import static fr.bbougon.iris.Configuration.configurationServeur;

public class Serveur {

    public Serveur(InetSocketAddress socketAddress) {
        serveur = new Server(socketAddress);
        WebAppContext context = new WebAppContext();
        context.setDescriptor(configurationServeur().getDescriptor());
        context.setResourceBase(".");
        context.setContextPath("/");
        serveur.setHandler(context);
        démarre();
    }

    public void démarre() {
        try {
            serveur.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            serveur.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return serveur.getURI().toString();
    }

    private Server serveur;
}
