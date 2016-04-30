package fr.bbougon.iris.web.ressources.utilitaires;

import com.google.gson.Gson;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONAdresse;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;

public class JSONContactTestBuilder {

    public JSONContactTestBuilder avecNom(String nom) {
        this.nom = nom;
        return this;
    }

    public JSONContactTestBuilder avecAdresse(JSONAdresse jsonAdresse) {
        this.jsonAdresse = jsonAdresse;
        return this;
    }

    public JSONContactTestBuilder défaut() {
        avecNom("Défaut");
        prénom = "Prénom";
        jsonAdresse = new JSONAdresse();
        jsonAdresse.numéro = "24";
        jsonAdresse.voie = "Route du médoc";
        jsonAdresse.codePostal = "33000";
        jsonAdresse.ville = "Bordeaux";
        return this;
    }

    public String toJson() {
        return new Gson().toJson(build());
    }

    public JSONContact build() {
        JSONContact jsonContact = new JSONContact();
        jsonContact.nom = nom;
        jsonContact.prénom = prénom;
        if(jsonAdresse != null) {
            jsonContact.adresse = jsonAdresse;
        }
        return jsonContact;
    }

    private String nom;
    private String prénom;
    private JSONAdresse jsonAdresse;
}
