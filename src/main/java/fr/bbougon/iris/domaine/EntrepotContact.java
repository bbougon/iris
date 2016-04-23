package fr.bbougon.iris.domaine;

import fr.bbougon.iris.entrepot.Entrepot;
import fr.bbougon.iris.entrepot.mongo.EntrepotMongo;
import org.mongolink.MongoSession;

public class EntrepotContact extends EntrepotMongo<Contact> implements Entrepot<Contact> {
    public EntrepotContact(MongoSession session) {
        super(session);
    }
}
