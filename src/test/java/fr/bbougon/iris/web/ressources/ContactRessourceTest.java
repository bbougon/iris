package fr.bbougon.iris.web.ressources;

import com.jayway.jsonpath.JsonPath;
import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONAdresse;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;
import fr.bbougon.iris.rules.AvecEntrepotsMemoire;
import fr.bbougon.iris.web.ressources.utilitaires.JSONContactTestBuilder;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.*;
import static org.fest.assertions.api.Assertions.assertThat;

public class ContactRessourceTest {

    @Test
    public void onPeutCréerUnContactEnFournissantSonNomEtSonIdentifiant() {
        String identifiant = UUID.randomUUID().toString();

        Response response = new ContactRessource().créeUnContact(identifiant, jsonContact());

        assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
        assertThat(response.getHeaderString("Location")).isEqualTo(ContactRessource.PATH + "/" + identifiant);
        String contact = (String) response.getEntity();
        assertThat((String) JsonPath.read(contact, "$.identifiant")).isEqualTo(identifiant);
    }

    private JSONContact jsonContact() {
        JSONContact jsonContact = new JSONContact();
        jsonContact.nom = "Bertrand";
        jsonContact.prenom = "Prénom";
        JSONAdresse jsonAdresse = new JSONAdresse();
        jsonAdresse.numero = "10";
        jsonAdresse.voie = "rue Marie-Laurencin";
        jsonAdresse.codePostal = "75012";
        jsonAdresse.ville = "Paris";
        jsonContact.adresse = jsonAdresse;
        jsonContact.email = "mail@mail.com";
        return jsonContact;
    }

    @Test
    public void leContactEstCorrectementPersisté() throws Exception {
        String identifiant = UUID.randomUUID().toString();

        new ContactRessource().créeUnContact(identifiant, jsonContact());

        Contact contact = Entrepots.contact().parId(identifiant);
        assertThat(contact.getIdentifiant().toString()).isEqualTo(identifiant);
        assertThat(contact.getNom()).isEqualTo("Bertrand");
        assertThat(contact.getPrenom()).isEqualTo("Prénom");
        assertThat(contact.getAdresse().getNumero()).isEqualTo("10");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue Marie-Laurencin");
        assertThat(contact.getAdresse().getCodePostal()).isEqualTo("75012");
        assertThat(contact.getAdresse().getVille()).isEqualTo("Paris");
        assertThat(contact.getEmail()).isEqualTo("mail@mail.com");
    }

    @Test
    public void onPeutMettreÀJourUnContact() {
        String identifiant = UUID.randomUUID().toString();
        Entrepots.contact().persiste(Contact.créer(identifiant, "Bertrand", "Bougon", null, null));

        JSONContact contactAttendu = new JSONContactTestBuilder().défaut().build();
        Response response = new ContactRessource().créeUnContact(identifiant, contactAttendu);

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(Entrepots.contact().tous()).hasSize(1);
        Contact contact = Entrepots.contact().parId(identifiant);
        assertThat(contact.getNom()).isEqualTo(contactAttendu.nom);
        assertThat(contact.getPrenom()).isEqualTo(contactAttendu.prenom);
        assertThat(contact.getAdresse().getNumero()).isEqualTo(contactAttendu.adresse.numero);
    }

    @Test
    public void onPeutRécupérerTousLesContacts() {
        String identifiant1 = UUID.randomUUID().toString();
        Entrepots.contact().persiste(Contact.créer(identifiant1, "Bertrand", "Bougon", null, null));
        Entrepots.contact().persiste(Contact.créer(UUID.randomUUID().toString(), "Alexis", "Bougon", null, null));
        Entrepots.contact().persiste(Contact.créer(UUID.randomUUID().toString(), "Aline", "Bougon", null, null));

        Response response = new ContactRessource().récupèreTousLesContacts();

        String contacts = (String) response.getEntity();
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat((String) JsonPath.read(contacts, "$[0].identifiant")).isEqualTo(identifiant1);
        assertThat((String) JsonPath.read(contacts, "$[0].nom")).isEqualTo("Bertrand");
        assertThat((String) JsonPath.read(contacts, "$[0].prenom")).isEqualTo("Bougon");
        assertThat((String) JsonPath.read(contacts, "$[1].nom")).isEqualTo("Alexis");
        assertThat((String) JsonPath.read(contacts, "$[1].prenom")).isEqualTo("Bougon");
        assertThat((String) JsonPath.read(contacts, "$[2].nom")).isEqualTo("Aline");
        assertThat((String) JsonPath.read(contacts, "$[2].prenom")).isEqualTo("Bougon");
    }

    @Test
    public void onPeutSupprimerUnContact() {
        String identifiant = UUID.randomUUID().toString();
        Entrepots.contact().persiste(Contact.créer(identifiant, "Bertrand", "Bougon", null, null));

        Response response = new ContactRessource().supprimeLeContact(identifiant);

        assertThat(response.getStatus()).isEqualTo(NO_CONTENT.getStatusCode());
        assertThat(Entrepots.contact().tous()).isEmpty();
    }

    @Test
    public void onNePeutSupprimerUnContactQuiNExistePas() {
        Response response = new ContactRessource().supprimeLeContact(UUID.randomUUID().toString());

        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.getStatusCode());
    }
    @Rule
    public AvecEntrepotsMemoire entrepotsMemoire = new AvecEntrepotsMemoire();

}