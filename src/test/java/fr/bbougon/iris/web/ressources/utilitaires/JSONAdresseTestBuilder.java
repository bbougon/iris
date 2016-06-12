package fr.bbougon.iris.web.ressources.utilitaires;

import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONAdresse;

public class JSONAdresseTestBuilder {

    public JSONAdresseTestBuilder avecNuméro(String numéro) {
        this.numéro = numéro;
        return this;
    }

    public JSONAdresse build() {
        JSONAdresse jsonAdresse = new JSONAdresse();
        jsonAdresse.numero = numéro;
        jsonAdresse.voie = voie;
        jsonAdresse.codePostal = codePostal;
        jsonAdresse.ville = ville;
        return jsonAdresse;
    }

    public JSONAdresseTestBuilder défaut() {
        numéro = "10";
        voie = "Rue Pourmann";
        codePostal = "33300";
        ville = "Bordeaux";
        return this;
    }

    private String numéro;
    private String voie;
    private String codePostal;
    private String ville;
}
