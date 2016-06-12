package fr.bbougon.iris.entrepot.mongo.mapping;

import fr.bbougon.iris.domaine.Contact;
import org.mongolink.domain.mapper.AggregateMap;

public class ContactMapping extends AggregateMap<Contact> {

    @Override
    public void map() {
        id().onProperty(element().getIdentifiant()).natural();
        property().onProperty(element().getNom());
        property().onProperty(element().getPrenom());
        property().onProperty(element().getAdresse());
    }
}
