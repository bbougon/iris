package fr.bbougon.iris.domaine;

import com.google.common.collect.Lists;

import java.util.List;
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
        if(!telephones.isEmpty()) {
            contact.metÀJour(nom, prénom, email, adresse, telephones);
        } else {
            contact.metÀJour(nom, prénom, email, adresse, null);
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

    public ContactTestBuilder avecUnTéléphone(Telephone telephone) {
        this.telephones.add(telephone);
        return this;
    }

    private UUID identifiant;
    private String nom;
    private String prénom = "prenom";
    private String email;
    private Adresse adresse = new AdresseTestBuilder().build();
    private List<Telephone> telephones = Lists.newArrayList();
}
