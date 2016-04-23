package fr.bbougon.iris.web.ressources;

import fr.bbougon.iris.rules.AvecServeurEmbarqué;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.CREATED;
import static org.fest.assertions.api.Assertions.assertThat;

public class ContactRessourceIntegrationTest {

    @Rule
    public AvecServeurEmbarqué serveur = new AvecServeurEmbarqué();

    @Test
    public void peuCréerUncontact() {
        Client client = ClientBuilder.newClient();
        UUID identifiant = UUID.randomUUID();
        Entity<String> entity = Entity.json("{nom:Bertrand}");

        Response response = client.target(serveur.getUrl()).path("contact").path(identifiant.toString()).request().put(entity);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }

}