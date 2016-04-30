package fr.bbougon.iris.web.ressources.utilitaires;

import com.google.gson.Gson;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONAdresse;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;

public class JSONContactForTest {

    public JSONContactForTest withName(String nom) {
        jsonContact = createContactWith(nom);
        jsonContact.adresse = createAdresse();
        return this;
    }

    public String toJson() {
        return new Gson().toJson(jsonContact);
    }

    private JSONAdresse createAdresse() {
        JSONAdresse jsonAdresse = new JSONAdresse();
        jsonAdresse.numéro = "24";
        jsonAdresse.voie = "Route du médoc";
        jsonAdresse.codePostal = "33000";
        jsonAdresse.ville = "Bordeaux";
        return jsonAdresse;
    }

    private JSONContact createContactWith(String nom) {
        jsonContact.nom = nom;
        jsonContact.prénom = "Prénom";
        return jsonContact;
    }

    private JSONContact jsonContact = new JSONContact();
}
