package fr.bbougon.iris.web.ressources;

import com.google.common.collect.Lists;
import com.jayway.jsonpath.JsonPath;
import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.domaine.TypeTelephone;
import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONAdresse;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONTelephone;
import fr.bbougon.iris.rules.AvecEntrepotsMemoire;
import fr.bbougon.iris.web.ressources.utilitaires.JSONContactTestBuilder;
import fr.bbougon.iris.web.ressources.utilitaires.JSONTéléphoneTestBuilder;
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
    }

    @Test
    public void onPeutMettreÀJourUnContact() {
        String identifiant = UUID.randomUUID().toString();
        Entrepots.contact().persiste(Contact.créer(identifiant, "Bertrand", "Bougon"));

        JSONContact contactAttendu = new JSONContactTestBuilder().défaut().build();
        Response response = new ContactRessource().créeUnContact(identifiant, contactAttendu);

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(Entrepots.contact().tous()).hasSize(1);
        Contact contact = Entrepots.contact().parId(identifiant);
        assertThat(contact.getNom()).isEqualTo(contactAttendu.nom);
        assertThat(contact.getPrenom()).isEqualTo(contactAttendu.prenom);
        assertThat(contact.getAdresse().getNumero()).isEqualTo(contactAttendu.adresse.numero);
        assertThat(contact.getEmail()).isEqualTo(contactAttendu.email);
    }

    @Test
    public void onPeutAjouterUnNuméroDeTéléphonePersonnelÀUnContact() {
        String identifiant = UUID.randomUUID().toString();
        Entrepots.contact().persiste(Contact.créer(identifiant, "Bertrand", "Bougon"));

        JSONContact contactAttendu = new JSONContactTestBuilder().avecNom("Bougon").avecPrénom("Bertrand").avecTéléphone("0666666666", TypeTelephone.PERSONNEL).build();
        Response response = new ContactRessource().créeUnContact(identifiant, contactAttendu);

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        Contact contact = Entrepots.contact().parId(identifiant);
        assertTelephone(contact, 0, "0666666666", TypeTelephone.PERSONNEL);
    }

    @Test
    public void onPeutAjouterUnNuméroDeTéléphoneProfessionnelÀUnContact() {
        String identifiant = UUID.randomUUID().toString();
        Entrepots.contact().persiste(Contact.créer(identifiant, "Bertrand", "Bougon"));

        JSONContact contactAttendu = new JSONContactTestBuilder().avecNom("Bougon").avecPrénom("Bertrand").avecTéléphone("0666666666", TypeTelephone.PROFESSIONNEL).build();
        Response response = new ContactRessource().créeUnContact(identifiant, contactAttendu);

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        Contact contact = Entrepots.contact().parId(identifiant);
        assertTelephone(contact, 0, "0666666666", TypeTelephone.PROFESSIONNEL);
    }

    @Test
    public void onPeutAjouterUneListeDeTéléphoneÀUnContact() {
        String identifiant = UUID.randomUUID().toString();
        Entrepots.contact().persiste(Contact.créer(identifiant, "Bertrand", "Bougon"));
        JSONTelephone téléphone = new JSONTéléphoneTestBuilder().avecNuméro("0666666666").avecType(TypeTelephone.PROFESSIONNEL).build();
        JSONTelephone téléphone1 = new JSONTéléphoneTestBuilder().avecNuméro("0666666665").avecType(TypeTelephone.PERSONNEL).build();
        JSONTelephone téléphone2 = new JSONTéléphoneTestBuilder().avecNuméro("0666666664").avecType(TypeTelephone.MOBILE).build();
        JSONTelephone téléphone3 = new JSONTéléphoneTestBuilder().avecNuméro("0666666663").avecType(TypeTelephone.PRINCIPAL).build();

        JSONContact contactAttendu = new JSONContactTestBuilder().avecNom("Bougon").avecPrénom("Bertrand").avecTéléphones(Lists.newArrayList(téléphone, téléphone1, téléphone2, téléphone3)).build();
        Response response = new ContactRessource().créeUnContact(identifiant, contactAttendu);

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        Contact contact = Entrepots.contact().parId(identifiant);
        assertTelephone(contact, 0, "0666666666", TypeTelephone.PROFESSIONNEL);
        assertTelephone(contact, 1, "0666666665", TypeTelephone.PERSONNEL);
        assertTelephone(contact, 2, "0666666664", TypeTelephone.MOBILE);
        assertTelephone(contact, 3, "0666666663", TypeTelephone.PRINCIPAL);

    }

    public void assertTelephone(Contact contact, int index, String numéroAttendu, TypeTelephone typeTéléphoneAttendu) {
        assertThat(contact.getTelephones().get(index).getNumero()).isEqualTo(numéroAttendu);
        assertThat(contact.getTelephones().get(index).getType()).isEqualTo(typeTéléphoneAttendu);
    }

    @Test
    public void onPeutRécupérerTousLesContacts() {
        String identifiant1 = UUID.randomUUID().toString();
        Entrepots.contact().persiste(Contact.créer(identifiant1, "Bertrand", "Bougon"));
        Entrepots.contact().persiste(Contact.créer(UUID.randomUUID().toString(), "Alexis", "Bougon"));
        Entrepots.contact().persiste(Contact.créer(UUID.randomUUID().toString(), "Aline", "Bougon"));

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
        Entrepots.contact().persiste(Contact.créer(identifiant, "Bertrand", "Bougon"));

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