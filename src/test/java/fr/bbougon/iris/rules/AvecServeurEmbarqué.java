package fr.bbougon.iris.rules;

import fr.bbougon.iris.Serveur;
import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepots;
import org.junit.rules.ExternalResource;

import java.net.InetSocketAddress;
import java.util.List;

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
        List<Contact> contacts = Entrepots.contact().tous();
        for (Contact contact : contacts) {
            Entrepots.contact().supprime(contact);
        }
        serveur.stop();
    }

    public String getUrl() {
        return serveur.getUrl();
    }

    private Serveur serveur;
}
