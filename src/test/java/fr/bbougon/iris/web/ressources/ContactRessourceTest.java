package fr.bbougon.iris.web.ressources;

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

import static javax.ws.rs.core.Response.Status.OK;
import static org.fest.assertions.api.Assertions.assertThat;

public class ContactRessourceTest {

    @Rule
    public AvecEntrepotsMemoire entrepotsMemoire = new AvecEntrepotsMemoire();

    @Test
    public void onPeutCréerUnContactEnFournissantSonNomEtSonIdentifiant() {
        String identifiant = UUID.randomUUID().toString();

        Response response = new ContactRessource().créeUnContact(identifiant, jsonContact());

        assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
        assertThat(response.getHeaderString("Location")).isEqualTo(ContactRessource.PATH + "/" + identifiant);
    }

    private JSONContact jsonContact() {
        JSONContact jsonContact = new JSONContact();
        jsonContact.nom = "Bertrand";
        jsonContact.prénom ="Prénom";
        JSONAdresse jsonAdresse = new JSONAdresse();
        jsonAdresse.numéro = "10";
        jsonAdresse.voie = "rue Marie-Laurencin";
        jsonAdresse.codePostal = "75012";
        jsonAdresse.ville = "Paris";
        jsonContact.adresse = jsonAdresse;
        return jsonContact;
    }

    @Test
    public void leContactEstCorrectementPersisté() {
        String identifiant = UUID.randomUUID().toString();

        new ContactRessource().créeUnContact(identifiant, jsonContact());

        Contact contact = Entrepots.contact().parId(identifiant);
        assertThat(contact.getIdentifiant().toString()).isEqualTo(identifiant);
        assertThat(contact.getNom()).isEqualTo("Bertrand");
        assertThat(contact.getPrénom()).isEqualTo("Prénom");
        assertThat(contact.getAdresse().getNuméro()).isEqualTo("10");
        assertThat(contact.getAdresse().getVoie()).isEqualTo("rue Marie-Laurencin");
        assertThat(contact.getAdresse().getCodePostal()).isEqualTo("75012");
        assertThat(contact.getAdresse().getVille()).isEqualTo("Paris");
    }

    @Test
    public void onPeutMettreÀJourUnContact() {
        String identifiant = UUID.randomUUID().toString();
        Entrepots.contact().persiste(Contact.créer(identifiant, "Bertrand", "Bougon", null));

        JSONContact contactAttendu = new JSONContactTestBuilder().défaut().build();
        Response response = new ContactRessource().créeUnContact(identifiant, contactAttendu);

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(Entrepots.contact().tous()).hasSize(1);
        Contact contact = Entrepots.contact().parId(identifiant);
        assertThat(contact.getNom()).isEqualTo(contactAttendu.nom);
        assertThat(contact.getPrénom()).isEqualTo(contactAttendu.prénom);
        assertThat(contact.getAdresse().getNuméro()).isEqualTo(contactAttendu.adresse.numéro);
    }

}