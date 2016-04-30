package fr.bbougon.iris.domaine;

import java.util.UUID;

public class Contact {

    @SuppressWarnings("Pour MongoLink")
    protected Contact() {}

    private Contact(String identifiant, String nom, String prénom) {
        this.identifiant = UUID.fromString(identifiant);
        this.nom = nom;
        this.prénom = prénom;
    }

    public static Contact créer(String identifiant, String nom, String prénom, Adresse adresse) {
        Contact contact = new Contact(identifiant, nom, prénom);
        contact.setAdresse(adresse);
        return contact;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public UUID getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    private UUID identifiant;
    private String nom;
    private String prénom;
    private Adresse adresse;
}
