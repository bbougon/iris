package fr.bbougon.iris.domaine;

import fr.bbougon.iris.web.ressources.JSONContact;

import java.util.UUID;

public class Contact {

    @SuppressWarnings("Pour MongoLink")
    protected Contact() {}

    private Contact(String identifiant, String nom) {
        this.identifiant = UUID.fromString(identifiant);
        this.nom = nom;
    }

    public static Contact créer(String identifiant, JSONContact jsonContact) {
        return new Contact(identifiant, jsonContact.nom);
    }

    public UUID getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    private UUID identifiant;
    private String nom;
}
