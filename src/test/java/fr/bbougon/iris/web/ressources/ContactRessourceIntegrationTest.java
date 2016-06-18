package fr.bbougon.iris.web.ressources;

import com.jayway.jsonpath.JsonPath;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;
import fr.bbougon.iris.rules.AvecServeurEmbarqué;
import fr.bbougon.iris.web.ressources.utilitaires.JSONContactTestBuilder;
import net.minidev.json.JSONArray;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.*;
import static org.fest.assertions.api.Assertions.assertThat;

public class ContactRessourceIntegrationTest {

    @Before
    public void before() {
        client = ClientBuilder.newClient();
    }

    @Test
    public void peutCréerUncontact() {
        UUID identifiant = UUID.randomUUID();
        Entity<String> entity = Entity.json(new JSONContactTestBuilder().défaut().toJson());

        Response response = client.target(serveur.getUrl()).path(ContactRessource.PATH).path(identifiant.toString()).request().put(entity);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }

    @Test
    public void badRequestSurUnContactAvecUnMauvaisUUID() {
        Response response = client.target(serveur.getUrl()).path(ContactRessource.PATH).path("mauvais-UUID").request().put(Entity.json(new JSONContactTestBuilder().défaut().toJson()));

        assertThat(response.getStatus()).isEqualTo(BAD_REQUEST.getStatusCode());
        assertThat(response.readEntity(String.class)).isEqualTo("L'identifiant 'mauvais-UUID' doit être au format UUID.");
    }

    @Test
    public void onPeutCréerUnContactSansAdresse() {
        UUID identifiant = UUID.randomUUID();
        Entity<String> entity = Entity.json(new JSONContactTestBuilder().avecNom("Luc").toJson());

        Response response = client.target(serveur.getUrl()).path(ContactRessource.PATH).path(identifiant.toString()).request().put(entity);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }

    @Test
    public void onPeutRécupérerUnContactAvecSonIdentifiant() {
        UUID identifiant = UUID.randomUUID();
        JSONContact contactAttendu = créeUnContact(identifiant);

        Response response = client.target(serveur.getUrl()).path(ContactRessource.PATH).path(identifiant.toString()).request().get();

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
        String contactRetourné = response.readEntity(String.class);
        assertThat((String) JsonPath.read(contactRetourné, "$.identifiant")).isEqualTo(identifiant.toString());
        assertThat((String) JsonPath.read(contactRetourné, "$.nom")).isEqualTo(contactAttendu.nom);
        assertThat((String) JsonPath.read(contactRetourné, "$.prenom")).isEqualTo(contactAttendu.prenom);
        assertThat((String) JsonPath.read(contactRetourné, "$.adresse.numero")).isEqualTo(contactAttendu.adresse.numero);
        assertThat((String) JsonPath.read(contactRetourné, "$.adresse.voie")).isEqualTo(contactAttendu.adresse.voie);
        assertThat((String) JsonPath.read(contactRetourné, "$.adresse.codePostal")).isEqualTo(contactAttendu.adresse.codePostal);
        assertThat((String) JsonPath.read(contactRetourné, "$.adresse.ville")).isEqualTo(contactAttendu.adresse.ville);
    }

    @Test
    public void notFoundAvecUnContactInconnu() {
        Response response = client.target(serveur.getUrl()).path(ContactRessource.PATH).path(UUID.randomUUID().toString()).request().get();

        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.getStatusCode());
    }

    @Test
    public void retourneLaListeDesContacts() {
        créeUnContact();

        Response response = client.target(serveur.getUrl()).path(ContactRessource.PATH).request().get();

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
        String entity = response.readEntity(String.class);
        assertThat((JSONArray) JsonPath.read(entity, "$")).hasSize(1);
        assertThat((String) JsonPath.read(entity, "$[0].identifiant")).isNotNull();
        assertThat((String) JsonPath.read(entity, "$[0].nom")).isEqualTo("Défaut");
    }

    @Test
    public void peutSupprimerUnContact() {
        UUID identifiant = UUID.randomUUID();
        créeUnContact(identifiant);

        Response response = client.target(serveur.getUrl()).path(ContactRessource.PATH).path(identifiant.toString()).request().delete();

        assertThat(response.getStatus()).isEqualTo(NO_CONTENT.getStatusCode());
    }

    public JSONContact créeUnContact() {
        return créeUnContact(UUID.randomUUID());
    }

    private JSONContact créeUnContact(UUID identifiant) {
        JSONContactTestBuilder contactTestBuilder = new JSONContactTestBuilder().défaut();
        client.target(serveur.getUrl()).path(ContactRessource.PATH).path(identifiant.toString()).request().put(Entity.json(contactTestBuilder.toJson()));
        return contactTestBuilder.build();
    }
    @Rule
    public AvecServeurEmbarqué serveur = new AvecServeurEmbarqué();
    private Client client;
}