package fr.bbougon.iris.domaine;

import fr.bbougon.iris.entrepot.Entrepot;
import fr.bbougon.iris.entrepot.mongo.EntrepotMongo;
import org.mongolink.MongoSession;
import org.mongolink.domain.criteria.Criteria;

import java.util.List;

import static org.mongolink.domain.criteria.Order.ASCENDING;

public class EntrepotContact extends EntrepotMongo<Contact> implements Entrepot<Contact> {
    public EntrepotContact(MongoSession session) {
        super(session);
    }

    @Override
    public List<Contact> tous() {
        Criteria criteria = session.createCriteria(persistentType());
        criteria.sort("nom", ASCENDING);
        return criteria.list();
    }
}
