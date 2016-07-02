package fr.bbougon.iris.web.ressources.utilitaires;

import fr.bbougon.iris.domaine.TypeTelephone;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONTelephone;

public class JSONTéléphoneTestBuilder {
    public JSONTéléphoneTestBuilder avecNuméro(String numéro) {
        this.numéro = numéro;
        return this;
    }

    public JSONTéléphoneTestBuilder avecType(TypeTelephone typeTéléphone) {
        this.typeTéléphone = typeTéléphone;
        return this;
    }

    public JSONTelephone build() {
        JSONTelephone jsonTelephone = new JSONTelephone();
        jsonTelephone.type = typeTéléphone;
        jsonTelephone.numero = numéro;
        return jsonTelephone;
    }

    private String numéro;
    private TypeTelephone typeTéléphone;
}
