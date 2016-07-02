package fr.bbougon.iris.entrepot.mongo.mapping;

import fr.bbougon.iris.domaine.Telephone;
import org.mongolink.domain.mapper.ComponentMap;

public class TelephoneMapping extends ComponentMap<Telephone> {
    @Override
    public void map() {
        property().onProperty(element().getNumero());
        property().onProperty(element().getType());
    }
}
