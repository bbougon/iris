package fr.bbougon.iris.entrepot.domaine;

import com.google.common.collect.Iterables;
import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepot;
import fr.bbougon.iris.entrepot.memoire.EntrepotMemoire;

public class EntrepotContactMemoire extends EntrepotMemoire<Contact> implements Entrepot<Contact> {

    @Override
    public Contact parId(String identifiant) {
        return Iterables.find(tous(), contact -> contact.getIdentifiant().toString().equals(identifiant));
    }
}
