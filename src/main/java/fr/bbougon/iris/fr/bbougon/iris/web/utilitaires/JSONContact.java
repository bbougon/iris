package fr.bbougon.iris.fr.bbougon.iris.web.utilitaires;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONContact {
    public String nom;
    public String prenom;
    public JSONAdresse adresse;
    public String email;
    public List<JSONTelephone> telephones;

    @Override
    public String toString() {
        return "JSONContact{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse=" + adresse +
                ", email='" + email + '\'' +
                ", telephones=" + telephones +
                '}';
    }
}
