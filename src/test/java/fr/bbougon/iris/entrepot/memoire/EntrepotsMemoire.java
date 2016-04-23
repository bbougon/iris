package fr.bbougon.iris.entrepot.memoire;

import fr.bbougon.iris.domaine.Contact;
import fr.bbougon.iris.entrepot.Entrepot;
import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.entrepot.domaine.EntrepotContactMemoire;

public class EntrepotsMemoire extends Entrepots {

    @Override
    protected Entrepot<Contact> getContact() {
        return entrepotContactMemoire;
    }

    private EntrepotContactMemoire entrepotContactMemoire = new EntrepotContactMemoire();
}
