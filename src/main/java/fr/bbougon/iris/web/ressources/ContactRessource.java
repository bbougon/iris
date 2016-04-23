package fr.bbougon.iris.web.ressources;

import com.google.gson.Gson;
import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepots;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path(ContactRessource.PATH)
public class ContactRessource {

    @PUT
    @Path("/{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response créeUnContact(@PathParam("identifiant") String identifiant, String jsonContent) {
        JSONContact jsonContact = new Gson().fromJson(jsonContent, JSONContact.class);
        Contact contact = Contact.créer(identifiant, jsonContact);
        Entrepots.contact().persiste(contact);
        return Response.created(UriBuilder.fromResource(this.getClass()).path(identifiant).build()).build();
    }

    public static final String PATH = "/contacts";
}
