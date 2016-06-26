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
        Contact contact = Contact.créer(identifiant.toString(), nom, prénom);
        if (null != adresse) {
            contact.setAdresse(adresse);
        }
        if (null != email) {
            contact.metÀJour(nom, prénom, email, adresse);
        }
        return contact;
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
