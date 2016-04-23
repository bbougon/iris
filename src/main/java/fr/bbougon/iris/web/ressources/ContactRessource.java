package fr.bbougon.iris.web.ressources;

import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepots;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path(ContactRessource.PATH)
public class ContactRessource {

    @PUT
    @Path("/{identifiant}")
    public Response créeUnContact(@PathParam("identifiant") String identifiant, String nom) {
        Contact contact = Contact.créer(identifiant, nom);
        Entrepots.contact().persiste(contact);
        return Response.created(UriBuilder.fromResource(this.getClass()).path(identifiant).build()).build();
    }

    public static final String PATH = "/contact";
}
