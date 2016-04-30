package fr.bbougon.iris.domaine;

import java.util.UUID;

public class ContactBuilderForTest {

    public ContactBuilderForTest avecIdentifiant(UUID identifiant) {
        this.identifiant = identifiant;
        return this;
    }

    public ContactBuilderForTest avecUnNom(String nom) {
        this.nom = nom;
        return this;
    }

    public Contact build() {
        return Contact.créer(identifiant.toString(), nom, prénom, adresse);
    }

    public ContactBuilderForTest avecUnPrénom(String prénom) {
        this.prénom = prénom;
        return this;
    }

    public ContactBuilderForTest avecUneAdresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    private UUID identifiant;
    private String nom;
    private String prénom = "prénom";
    private Adresse adresse = new AdresseBuilderForTest().build();

}
