package fr.bbougon.iris.web.ressources.utilitaires;

import com.google.gson.Gson;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONAdresse;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;

public class JSONContactTestBuilder {

    public JSONContactTestBuilder avecNom(String nom) {
        this.nom = nom;
        return this;
    }

    public JSONContactTestBuilder défaut() {
        avecNom("Défaut");
        prenom = "Prénom";
        jsonAdresse = new JSONAdresse();
        jsonAdresse.numero = "24";
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
        jsonContact.prenom = prenom;
        if (jsonAdresse != null) {
            jsonContact.adresse = jsonAdresse;
        }
        return jsonContact;
    }

    private String nom;
    private String prenom;
    private JSONAdresse jsonAdresse;
}
