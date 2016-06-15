package fr.bbougon.iris.domaine;

public class Adresse {

    protected Adresse() {
    }

    public static Adresse créer(String numéro, String voie, String codePostal, String ville) {
        Adresse adresse = new Adresse();
        adresse.numero = numéro;
        adresse.voie = voie;
        adresse.codePostal = codePostal;
        adresse.ville = ville;
        return adresse;
    }

    public String getNumero() {
        return numero;
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

    private String numero;
    private String voie;
    private String codePostal;
    private String ville;
}
