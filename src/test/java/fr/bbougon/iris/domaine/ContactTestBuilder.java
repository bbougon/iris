package fr.bbougon.iris.domaine;

import java.util.UUID;

public class ContactTestBuilder {

    public ContactTestBuilder avecIdentifiant(UUID identifiant) {
        this.identifiant = identifiant;
        return this;
    }

    public ContactTestBuilder avecUnNom(String nom) {
        this.nom = nom;
        return this;
    }

    public Contact build() {
        return Contact.créer(identifiant.toString(), nom, prénom, email, adresse);
    }

    public ContactTestBuilder avecUnPrénom(String prénom) {
        this.prénom = prénom;
        return this;
    }

    public ContactTestBuilder avecUneAdresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    public ContactTestBuilder avecUnEmail(String email) {
        this.email = email;
        return this;
    }

    private UUID identifiant;
    private String nom;
    private String prénom = "prenom";
    private String email;
    private Adresse adresse = new AdresseTestBuilder().build();
}
