package fr.bbougon.iris.entrepot.mongo;

import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.domaine.EntrepotContact;
import fr.bbougon.iris.entrepot.Entrepot;
import fr.bbougon.iris.entrepot.Entrepots;
import org.mongolink.MongoSession;

import static fr.bbougon.iris.entrepot.mongo.MongoConfiguration.session;

public class EntrepotsMongos extends Entrepots {
    public EntrepotsMongos(MongoSession session) {
        this.session = session;
    }

    @Override
    protected Entrepot<Contact> getContact() {
        return new EntrepotContact(session);
    }

    private final MongoSession session;
}
