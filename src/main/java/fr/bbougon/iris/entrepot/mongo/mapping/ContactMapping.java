package fr.bbougon.iris.entrepot.mongo.mapping;

import com.google.gson.Gson;
import fr.bbougon.iris.domaine.Contact;
import org.mongolink.domain.mapper.AggregateMap;

public class ContactMapping extends AggregateMap<Contact> {

    @Override
    public void map() {
        id().onProperty(element().getIdentifiant()).natural();
        property().onProperty(element().getNom());
        property().onProperty(element().getPrenom());
        property().onProperty(element().getAdresse());
        property().onProperty(new Gson().toJson(element().getEmail()));
        collection().onProperty(element().getTelephones());
    }
}
