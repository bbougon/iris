package fr.bbougon.iris.web.ressources;

import fr.bbougon.iris.rules.AvecServeurEmbarqué;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.fest.assertions.api.Assertions.assertThat;

public class ContactRessourceIntegrationTest {

    @Rule
    public AvecServeurEmbarqué serveur = new AvecServeurEmbarqué();

    @Before
    public void before() {
        client = ClientBuilder.newClient();
    }

    @Test
    public void peutCréerUncontact() {
        UUID identifiant = UUID.randomUUID();
        Entity<String> entity = Entity.json("{nom:Bertrand}");

        Response response = client.target(serveur.getUrl()).path(ContactRessource.PATH).path(identifiant.toString()).request().put(entity);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }

    @Test
    public void badRequestSurUnContactAvecUnMauvaisUUID() {
        Response response = client.target(serveur.getUrl()).path(ContactRessource.PATH).path("mauvais-UUID").request().put(Entity.json("{nom:un-nom}"));

        assertThat(response.getStatus()).isEqualTo(BAD_REQUEST.getStatusCode());
        assertThat(response.readEntity(String.class)).isEqualTo("L'identifiant 'mauvais-UUID' doit être au format UUID.");
    }

    private Client client;
}