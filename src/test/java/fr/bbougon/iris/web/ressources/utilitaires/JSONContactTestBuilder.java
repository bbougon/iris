package fr.bbougon.iris.web.ressources.utilitaires;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import fr.bbougon.iris.domaine.TypeTelephone;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONAdresse;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONTelephone;

import java.util.List;

public class JSONContactTestBuilder {

    public JSONContactTestBuilder avecNom(String nom) {
        this.nom = nom;
        return this;
    }

    public JSONContactTestBuilder défaut() {
        avecNom("Défaut");
        prenom = "Prénom";
        email = "mail@mail.com";
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
        jsonContact.email = email;
        if (jsonAdresse != null) {
            jsonContact.adresse = jsonAdresse;
        }
        if(!jsonTelephoneList.isEmpty()) {
            jsonContact.telephones = Lists.newArrayList();
            jsonContact.telephones.addAll(jsonTelephoneList);
        }
        return jsonContact;
    }

    public JSONContactTestBuilder avecPrénom(String prénom) {
        prenom = prénom;
        return this;
    }

    public JSONContactTestBuilder avecTéléphone(String numéro, TypeTelephone typeTelephone) {
        JSONTelephone jsonTelephone = new JSONTelephone();
        jsonTelephone.numero = numéro;
        jsonTelephone.type = typeTelephone;
        jsonTelephoneList.add(jsonTelephone);
        return this;
    }

    public JSONContactTestBuilder avecTéléphones(List<JSONTelephone> telephones) {
        jsonTelephoneList.addAll(telephones);
        return this;
    }

    private String nom;
    private String prenom;
    private String email;
    private JSONAdresse jsonAdresse;
    private List<JSONTelephone> jsonTelephoneList = Lists.newArrayList();
}
