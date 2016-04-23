package fr.bbougon.iris.rules;

import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.entrepot.memoire.EntrepotsMemoire;
import org.junit.rules.ExternalResource;

public class AvecEntrepotsMemoire extends ExternalResource {

    public AvecEntrepotsMemoire() {
        Entrepots.initialise(new EntrepotsMemoire());
    }

}
