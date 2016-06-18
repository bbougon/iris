package fr.bbougon.iris.domaine;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Optional;
import java.util.UUID;

public class Contact {

    @SuppressWarnings("Pour MongoLink")
    protected Contact() {
    }

    private Contact(String identifiant, String nom, String prenom) {
        this.identifiant = UUID.fromString(identifiant);
        this.nom = nom;
        this.prenom = prenom;
    }

    public static Contact créer(String identifiant, String nom, String prénom, String email, Adresse adresse) {
        Contact contact = new Contact(identifiant, nom, prénom);
        contact.setAdresse(adresse);
        contact.setEmail(email);
        return contact;
    }

    public UUID getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void metÀJour(String nom, String prénom, String email, Adresse adresse) {
        this.nom = Optional.ofNullable(nom).filter(s -> !nom.equals("")).orElse(this.nom);
        this.prenom = Optional.ofNullable(prénom).filter(s -> !prénom.equals("")).orElse(this.prenom);
        this.adresse = Optional.ofNullable(metÀJour(adresse)).orElse(this.adresse);
        setEmail(email);
    }

    private Adresse metÀJour(Adresse adresse) {
        if (null != adresse) {
            Optional<String> numéroOptionnel = getOptionalPour(adresse.getNumero());
            Optional<String> voieOptionnelle = getOptionalPour(adresse.getVoie());
            Optional<String> codePostalOptionnel = getOptionalPour(adresse.getCodePostal());
            Optional<String> villeOptionnelle = getOptionalPour(adresse.getVille());
            if (null != this.adresse) {
                return créeUneAdresse(numéroOptionnel, voieOptionnelle, codePostalOptionnel, villeOptionnelle);

            }
            return Adresse.créer(numéroOptionnel.get(), voieOptionnelle.get(), codePostalOptionnel.get(), villeOptionnelle.get());
        }
        return null;
    }

    private Adresse créeUneAdresse(Optional<String> numéroOptionnel, Optional<String> voieOptionnelle, Optional<String> codePostalOptionnel, Optional<String> villeOptionnelle) {
        String numéro = numéroOptionnel.orElse(this.adresse.getNumero());
        String voie = voieOptionnelle.orElse(this.adresse.getVoie());
        String codePostal = codePostalOptionnel.orElse(this.adresse.getCodePostal());
        String ville = villeOptionnelle.orElse(this.adresse.getVille());
        return Adresse.créer(numéro, voie, codePostal, ville);
    }

    private Optional<String> getOptionalPour(String valeur) {
        return Optional.of(valeur).filter(s -> !valeur.equals(""));
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        try {
            Optional<String> optionalEmail = Optional.ofNullable(email).filter(s -> !email.equals(""));
            if (optionalEmail.isPresent()) {
                new InternetAddress(optionalEmail.get(), true);
                this.email = optionalEmail.get();
            }
        } catch (AddressException e) {
            throw new EmailInvalideException(email);
        }
    }
    private UUID identifiant;
    private String nom;
    private String prenom;
    private Adresse adresse;
    private String email;
}
