package fr.bbougon.iris.fr.bbougon.iris.web.utilitaires;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONContact {
    public String nom;
    public String prenom;
    public JSONAdresse adresse;
}
