package fr.bbougon.iris.domaine;

import java.util.UUID;

public class Contact {

    @SuppressWarnings("Pour MongoLink")
    protected Contact() {}

    private Contact(String identifiant, String nom) {
        this.identifiant = UUID.fromString(identifiant);
        this.nom = nom;
    }

    public static Contact créer(String identifiant, String nom) {
        return new Contact(identifiant, nom);
    }

    public UUID getIdentifiant() {
        return identifiant;
    }

    private UUID identifiant;
    private String nom;
}
