package fr.bbougon.iris.domaine;

public class Adresse {

    protected Adresse() {}

    public static Adresse créer(String numéro, String voie, String codePostal, String ville) {
        Adresse adresse = new Adresse();
        adresse.numéro = numéro;
        adresse.voie = voie;
        adresse.codePostal = codePostal;
        adresse.ville = ville;
        return adresse;
    }

    public String getNuméro() {
        return numéro;
    }

    public String getVoie() {
        return voie;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    private String numéro;
    private String voie;
    private String codePostal;
    private String ville;
}
