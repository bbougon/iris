package fr.bbougon.iris.domaine;

public class AdresseBuilderForTest {

    Adresse build() {
        return Adresse.créer(numéro, voie, codePostal, ville);
    }

    public AdresseBuilderForTest withNuméro(String numéro) {
        this.numéro = numéro;
        return this;
    }

    public AdresseBuilderForTest withVoie(String voie) {
        this.voie = voie;
        return this;
    }

    public AdresseBuilderForTest withCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public AdresseBuilderForTest withVille(String ville) {
        this.ville = ville;
        return this;
    }

    private String numéro = "10";
    private String voie = "Cours de la somme";
    private String codePostal = "33000";
    private String ville = "Bordeaux";
}
