package fr.bbougon.iris.web.ressources;

import com.google.gson.Gson;
import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepots;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path(ContactRessource.PATH)
public class ContactRessource {

    @PUT
    @Path("/{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public Response créeUnContact(@PathParam("identifiant") String identifiant, String jsonContent) {
        try {
            JSONContact jsonContact = new Gson().fromJson(jsonContent, JSONContact.class);
            Contact contact = Contact.créer(identifiant, jsonContact);
            Entrepots.contact().persiste(contact);
            return Response.created(UriBuilder.fromResource(this.getClass()).path(identifiant).build()).entity("test").build();
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("L'identifiant '" + identifiant + "' doit être au format UUID.").build());
        }

    }

    public static final String PATH = "/contacts";
}
