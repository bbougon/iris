package fr.bbougon.iris.entrepot;

import fr.bbougon.iris.domaine.Contact;

public abstract class Entrepots {

    public static void initialise(Entrepots entrepots) {
        Entrepots.instance = entrepots;
    }

    public static Entrepot<Contact> contact() {
        return instance.getContact();
    }

    protected abstract Entrepot<Contact> getContact();

    private static Entrepots instance;

}
