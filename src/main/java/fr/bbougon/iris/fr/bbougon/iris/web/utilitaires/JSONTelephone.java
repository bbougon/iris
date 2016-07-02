package fr.bbougon.iris.fr.bbougon.iris.web.utilitaires;

import fr.bbougon.iris.domaine.TypeTelephone;

public class JSONTelephone {
    public String numero;
    public TypeTelephone type;

    @Override
    public String toString() {
        return "JSONTelephone{" +
                "numero='" + numero + '\'' +
                ", type=" + type +
                '}';
    }
}
