package fr.bbougon.iris.web.ressources;

import fr.bbougon.iris.domaine.Adresse;
import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONAdresse;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;

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
    public Response créeUnContact(@PathParam("identifiant") String identifiant, JSONContact jsonContact) {
        try {
            Contact contact = Contact.créer(identifiant, jsonContact.nom, jsonContact.prénom, créeUneAdresse(jsonContact));
            Entrepots.contact().persiste(contact);
            return Response.created(UriBuilder.fromResource(this.getClass()).path(identifiant).build()).entity("test").build();
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("L'identifiant '" + identifiant + "' doit être au format UUID.").build());
        }

    }

    public Adresse créeUneAdresse(JSONContact jsonContact) {
        JSONAdresse jsonAdresse = jsonContact.adresse;
        return Adresse.créer(jsonAdresse.numéro, jsonAdresse.voie, jsonAdresse.codePostal, jsonAdresse.ville);
    }

    public static final String PATH = "/contacts";
}
