package fr.bbougon.iris.entrepot.mongo.mapping;

import fr.bbougon.iris.domaine.Adresse;
import org.mongolink.domain.mapper.ComponentMap;

public class AdresseMapping extends ComponentMap<Adresse> {

    @Override
    public void map() {
        property().onProperty(element().getNumero());
        property().onProperty(element().getVoie());
        property().onProperty(element().getCodePostal());
        property().onProperty(element().getVille());
    }
}
