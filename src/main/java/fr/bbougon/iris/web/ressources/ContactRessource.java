package fr.bbougon.iris.web.ressources;

import com.google.gson.Gson;
import fr.bbougon.iris.domaine.Adresse;
import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.entrepot.mongo.IrisContainerRequestFilter;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONAdresse;
import fr.bbougon.iris.fr.bbougon.iris.web.utilitaires.JSONContact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path(ContactRessource.PATH)
public class ContactRessource {

    @PUT
    @Path("/{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response créeUnContact(@PathParam("identifiant") String identifiant, JSONContact jsonContact) {
        try {
            Contact contactExistant = Entrepots.contact().parId(identifiant);
            if (contactExistant != null) {
                contactExistant.metÀJour(jsonContact.nom, jsonContact.prenom, jsonContact.email, créeUneAdresse(jsonContact));
                return Response.ok().build();
            }
            Contact contact = Contact.créer(identifiant, jsonContact.nom, jsonContact.prenom);
            Entrepots.contact().persiste(contact);
            return Response.created(UriBuilder.fromResource(this.getClass()).path(identifiant).build()).entity(new Gson().toJson(contact)).build();
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("L'identifiant '" + identifiant + "' doit être au format UUID.").build());
        }

    }

    @GET
    @Path("/{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response récupèreUnContact(@PathParam("identifiant") String identifiant) {
        Contact contact = Entrepots.contact().parId(identifiant);
        if (null == contact) {
            return Response.status(NOT_FOUND).build();
        }
        return Response.ok(new Gson().toJson(contact)).build();
    }


    public Adresse créeUneAdresse(JSONContact jsonContact) {
        JSONAdresse jsonAdresse = jsonContact.adresse;
        return jsonAdresse == null ? null : Adresse.créer(jsonAdresse.numero, jsonAdresse.voie, jsonAdresse.codePostal, jsonAdresse.ville);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response récupèreTousLesContacts() {
        LOGGER.info("contacts");
        List<Contact> contacts = Entrepots.contact().tous();
        return Response.ok(new Gson().toJson(contacts)).build();
    }

    @DELETE
    @Path("/{identifiant}")
    public Response supprimeLeContact(@PathParam("identifiant") String identifiant) {
        Contact contact = Entrepots.contact().parId(identifiant);
        if (null == contact) {
            return Response.status(NOT_FOUND).build();
        }
        Entrepots.contact().supprime(contact);
        return Response.noContent().build();
    }

    public static final String PATH = "/contacts";
    private static final Logger LOGGER = LogManager.getLogger(IrisContainerRequestFilter.class.getCanonicalName());
}
