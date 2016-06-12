package fr.bbougon.iris.domaine;

import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;

public class ContactTest {

    @Test
    public void onPeutMettreÀJourUnePartieDuContact() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"));

        contact.metÀJour("", "", Adresse.créer("9", "rue Pourmann APPT 1143", "33300", "Bordeaux"));

        assertThat(contact.getNom()).isEqualTo("Bougon");
        assertThat(contact.getPrenom()).isEqualTo("Bertrand");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue Pourmann APPT 1143");
    }

    @Test
    public void onNeChangePasLAdresseSiNulle() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"));

        contact.metÀJour("Bougon", "Bertrand", null);

        assertThat(contact.getAdresse().getNumero()).isEqualTo("9");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue Pourmann");
        assertThat(contact.getAdresse().getCodePostal()).isEqualTo("33300");
        assertThat(contact.getAdresse().getVille()).isEqualTo("Bordeaux");
    }

    @Test
    public void onNeChangePasLAdresseSiVide() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"));

        contact.metÀJour("Bougon", "Bertrand", Adresse.créer("", "", "33300", "Bacalan"));

        assertThat(contact.getAdresse().getNumero()).isEqualTo("9");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue Pourmann");
        assertThat(contact.getAdresse().getCodePostal()).isEqualTo("33300");
        assertThat(contact.getAdresse().getVille()).isEqualTo("Bacalan");
    }

    @Test
    public void onNeChangePasLAdresseSiNulleÀLaCréationEtÀLaMiseÀJour() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand", null);

        contact.metÀJour("Bougon", "Bertrand", null);

        assertThat(contact.getAdresse()).isNull();
    }

    @Test
    public void onPeutMettreÀJourLAdresse() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand", null);

        contact.metÀJour("Bougon", "Bertrand", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"));

        assertThat(contact.getAdresse().getNumero()).isEqualTo("9");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue Pourmann");
        assertThat(contact.getAdresse().getCodePostal()).isEqualTo("33300");
        assertThat(contact.getAdresse().getVille()).isEqualTo("Bordeaux");
    }

}