package fr.bbougon.iris.fr.bbougon.iris.web.utilitaires;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONAdresse {
    public String numero;
    public String voie;
    public String codePostal;
    public String ville;

    @Override
    public String toString() {
        return "JSONAdresse{" +
                "numero='" + numero + '\'' +
                ", voie='" + voie + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
