package fr.bbougon.iris.rules;

import fr.bbougon.iris.Serveur;
import org.junit.rules.ExternalResource;

import java.net.InetSocketAddress;

public class AvecServeurEmbarqué extends ExternalResource {

    @Override
    public void before() {
        serveur = new Serveur(new InetSocketAddress(8080));
        démarre();
    }

    private void démarre() {
        serveur.démarre();
    }

    @Override
    public void after() {
        serveur.stop();
    }

    public String getUrl() {
        return serveur.getUrl();
    }

    private Serveur serveur;
}
