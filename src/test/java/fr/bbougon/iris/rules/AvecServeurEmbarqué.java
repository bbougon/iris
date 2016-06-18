package fr.bbougon.iris.rules;

import fr.bbougon.iris.Serveur;
import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.entrepot.mongo.EntrepotsMongos;
import fr.bbougon.iris.entrepot.mongo.MongoConfiguration;
import org.junit.rules.ExternalResource;
import org.mongolink.MongoSession;

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
        nettoieLesEntrepots();
        serveur.stop();
    }

    public void nettoieLesEntrepots() {
        MongoSession session = MongoConfiguration.createSession();
        session.start();
        Entrepots.initialise(new EntrepotsMongos(session));
        List<Contact> contacts = Entrepots.contact().tous();
        for (Contact contact : contacts) {
            Entrepots.contact().supprime(contact);
            session.flush();
        }
        session.stop();
    }

    public String getUrl() {
        return serveur.getUrl();
    }

    private Serveur serveur;
}
