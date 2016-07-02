package fr.bbougon.iris.domaine;

import com.google.common.collect.Lists;
import org.junit.Test;

import javax.mail.internet.AddressException;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown;

public class ContactTest {

    @Test
    public void onPeutMettreÀJourUnePartieDuContact() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");

        contact.metÀJour("", "", null, Adresse.créer("9", "rue Pourmann APPT 1143", "33300", "Bordeaux"), null);

        assertThat(contact.getNom()).isEqualTo("Bougon");
        assertThat(contact.getPrenom()).isEqualTo("Bertrand");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue Pourmann APPT 1143");
    }

    @Test
    public void onNeChangePasLAdresseSiNulle() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");
        contact.metÀJour("Bougon", "Bertrand", "mail@mail.com", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"), null);

        contact.metÀJour("Bougon", "Bertrand", null, null, null);

        assertThat(contact.getAdresse().getNumero()).isEqualTo("9");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue Pourmann");
        assertThat(contact.getAdresse().getCodePostal()).isEqualTo("33300");
        assertThat(contact.getAdresse().getVille()).isEqualTo("Bordeaux");
    }

    @Test
    public void onNeChangePasLAdresseSiVide() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");
        contact.metÀJour("Bougon", "Bertrand", "mail@mail.com", Adresse.créer("9", "rue Pourmann", "33300", "Bacalan"), null);

        contact.metÀJour("Bougon", "Bertrand", null, Adresse.créer("", "", "33300", "Bacalan"), null);

        assertThat(contact.getAdresse().getNumero()).isEqualTo("9");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue Pourmann");
        assertThat(contact.getAdresse().getCodePostal()).isEqualTo("33300");
        assertThat(contact.getAdresse().getVille()).isEqualTo("Bacalan");
    }

    @Test
    public void onNeChangePasLAdresseSiNulleÀLaCréationEtÀLaMiseÀJour() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");

        contact.metÀJour("Bougon", "Bertrand", null, null, null);

        assertThat(contact.getAdresse()).isNull();
    }

    @Test
    public void onPeutMettreÀJourLAdresse() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");
        contact.metÀJour("Bougon", "Bertrand", null, Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"), null);

        contact.metÀJour("Bougon", "Bertrand", null, Adresse.créer("11", "rue des remparts", "33000", "Bordeaux"), null);

        assertThat(contact.getAdresse().getNumero()).isEqualTo("11");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue des remparts");
        assertThat(contact.getAdresse().getCodePostal()).isEqualTo("33000");
        assertThat(contact.getAdresse().getVille()).isEqualTo("Bordeaux");
    }

    @Test
    public void onPeutMettreÀJourLeMail() throws AddressException {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");

        contact.metÀJour("Bougon", "Bertrand", "mail@mail.com", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"), null);

        assertThat(contact.getEmail()).isEqualTo("mail@mail.com");
    }

    @Test
    public void onPeutGérerLesEmailsInvalides() {
        try {
            Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");
            contact.metÀJour("Bougon", "Bertrand", "mailinvalide", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"), null);
            failBecauseExceptionWasNotThrown(EmailInvalideException.class);
        } catch (EmailInvalideException e) {
            assertThat(e.getMessage()).isEqualTo("L'email fourni 'mailinvalide' n'est pas un format d'email valide.");
        }
    }

    @Test
    public void onFiltreLesTéléphonesVides() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");
        Telephone emptyTéléphone = Telephone.créer(null, null);

        contact.metÀJour("Bougon", "Bertrand", "mail@mail.com", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"), Lists.newArrayList(emptyTéléphone));

        assertThat(contact.getTelephones()).isEmpty();
    }

    @Test
    public void onAjouteSeulementLesTéléphonesNonPrésents() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");
        Telephone téléphone = Telephone.créer("0666666666", TypeTelephone.PRINCIPAL);
        contact.metÀJour("Bougon", "Bertrand", "mail@mail.com", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"), Lists.newArrayList(téléphone));

        Telephone téléphoneÀAjouter1 = Telephone.créer("0666666666", TypeTelephone.PRINCIPAL);
        Telephone téléphoneÀAjouter2 = Telephone.créer("0666666655", TypeTelephone.MOBILE);
        contact.metÀJour("Bougon", "Bertrand", "mail@mail.com", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"), Lists.newArrayList(téléphoneÀAjouter1, téléphoneÀAjouter2));

        assertThat(contact.getTelephones()).hasSize(2);
        assertThat(contact.getTelephones().get(0)).isEqualTo(téléphone);
        assertThat(contact.getTelephones().get(1)).isEqualTo(téléphoneÀAjouter2);
    }

    @Test
    public void onPeutMettreÀJourUnTéléphone() {
        Contact contact = Contact.créer(UUID.randomUUID().toString(), "Bougon", "Bertrand");
        Telephone téléphone = Telephone.créer("0666666666", TypeTelephone.PRINCIPAL);
        contact.metÀJour("Bougon", "Bertrand", "mail@mail.com", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"), Lists.newArrayList(téléphone));

        Telephone téléphoneMisÀJour = Telephone.créer("0666666666", TypeTelephone.MOBILE);
        contact.metÀJour("Bougon", "Bertrand", "mail@mail.com", Adresse.créer("9", "rue Pourmann", "33300", "Bordeaux"), Lists.newArrayList(téléphoneMisÀJour));

        assertThat(contact.getTelephones()).hasSize(1);
        assertThat(contact.getTelephones().get(0).getNumero()).isEqualTo("0666666666");
        assertThat(contact.getTelephones().get(0).getType()).isEqualTo(TypeTelephone.MOBILE);
    }

}