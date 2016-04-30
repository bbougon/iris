package fr.bbougon.iris.domaine;

import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.rules.AvecEntrepotsMongo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;

public class EntrepotContactTest {

    @Rule
    public AvecEntrepotsMongo entrepots = new AvecEntrepotsMongo();

    @Before
    public void before() throws Exception {
        adresse = new AdresseTestBuilder()
                .withNuméro("10")
                .withVoie("Avenue Magenta")
                .withCodePostal("75010")
                .withVille("Paris")
                .build();
        contact = new ContactTestBuilder()
                .avecIdentifiant(UUID.randomUUID())
                .avecUnNom("Un Nom")
                .avecUnPrénom("Un prénom")
                .avecUneAdresse(adresse)
                .build();

    }

    @Test
    public void onPeutPersister() {
        Entrepots.contact().persiste(contact);
        entrepots.cleanSession();

        Contact contactRécupéré = Entrepots.contact().parId(contact.getIdentifiant().toString());

        assertThat(contactRécupéré).isNotNull();
        assertThat(contactRécupéré.getNom()).isEqualTo("Un Nom");
        assertThat(contactRécupéré.getPrénom()).isEqualTo("Un prénom");
        assertThat(contactRécupéré.getAdresse().getNuméro()).isEqualTo("10");
        assertThat(contactRécupéré.getAdresse().getVoie()).isEqualTo("Avenue Magenta");
        assertThat(contactRécupéré.getAdresse().getCodePostal()).isEqualTo("75010");
        assertThat(contactRécupéré.getAdresse().getVille()).isEqualTo("Paris");
    }

    @Test
    public void onPeutRécupérerTousLesContacts() {
        ContactTestBuilder contactTestBuilder = new ContactTestBuilder();
        Entrepots.contact().persiste(contactTestBuilder.avecUnNom("Bertrand").avecIdentifiant(UUID.randomUUID()).build());
        Entrepots.contact().persiste(contactTestBuilder.avecUnNom("Aline").avecIdentifiant(UUID.randomUUID()).build());
        Entrepots.contact().persiste(contactTestBuilder.avecUnNom("Alessandra").avecIdentifiant(UUID.randomUUID()).build());
        Entrepots.contact().persiste(contactTestBuilder.avecUnNom("Rafael").avecIdentifiant(UUID.randomUUID()).build());
        entrepots.cleanSession();

        List<Contact> contacts = Entrepots.contact().tous();

        assertThat(contacts).isNotEmpty();
        assertThat(contacts).hasSize(4);
        assertThat(contacts.get(0).getNom()).isEqualTo("Alessandra");
        assertThat(contacts.get(1).getNom()).isEqualTo("Aline");
        assertThat(contacts.get(2).getNom()).isEqualTo("Bertrand");
        assertThat(contacts.get(3).getNom()).isEqualTo("Rafael");
    }

    private Contact contact;
    private Adresse adresse;
}