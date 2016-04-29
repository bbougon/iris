package fr.bbougon.iris.web.ressources;

import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;
import fr.bbougon.iris.rules.AvecEntrepotsMemoire;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.UUID;

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
        return jsonContact;
    }

    @Test
    public void leContactEstCorrectementPersisté() {
        String identifiant = UUID.randomUUID().toString();

        new ContactRessource().créeUnContact(identifiant, jsonContact());

        Contact contact = Entrepots.contact().parId(identifiant);
        assertThat(contact.getIdentifiant().toString()).isEqualTo(identifiant);
        assertThat(contact.getNom()).isEqualTo("Bertrand");
    }

}