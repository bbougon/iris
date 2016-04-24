package fr.bbougon.iris.domaine;

import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.rules.AvecEntrepotsMongo;
import fr.bbougon.iris.web.ressources.JSONContact;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;

public class EntrepotContactTest {

    @Rule
    public AvecEntrepotsMongo entrepots = new AvecEntrepotsMongo();

    @Test
    public void onPeutPersister() {
        UUID identifiant = UUID.randomUUID();
        Contact contact = unContact(identifiant, "Un Nom");
        Entrepots.contact().persiste(contact);
        entrepots.cleanSession();

        Contact contactRécupéré = Entrepots.contact().parId(identifiant.toString());

        assertThat(contactRécupéré).isNotNull();
        assertThat(contactRécupéré.getNom()).isEqualTo("Un Nom");
    }

    @Test
    public void onPeutRécupérerTousLesContacts(){
        Entrepots.contact().persiste(unContact(UUID.randomUUID(), "Bertrand"));
        Entrepots.contact().persiste(unContact(UUID.randomUUID(), "Aline"));
        Entrepots.contact().persiste(unContact(UUID.randomUUID(), "Alessandra"));
        Entrepots.contact().persiste(unContact(UUID.randomUUID(), "Rafael"));
        entrepots.cleanSession();

        List<Contact> contacts = Entrepots.contact().tous();

        assertThat(contacts).isNotEmpty();
        assertThat(contacts).hasSize(4);
        assertThat(contacts.get(0).getNom()).isEqualTo("Alessandra");
        assertThat(contacts.get(1).getNom()).isEqualTo("Aline");
        assertThat(contacts.get(2).getNom()).isEqualTo("Bertrand");
        assertThat(contacts.get(3).getNom()).isEqualTo("Rafael");
    }

    private Contact unContact(UUID identifiant, String nom) {
        JSONContact jsonContact = new JSONContact();
        jsonContact.nom = nom;
        return Contact.créer(identifiant.toString(), jsonContact);
    }

}