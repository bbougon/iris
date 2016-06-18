package fr.bbougon.iris.domaine;

public class EmailInvalideException  extends RuntimeException{
    public EmailInvalideException(String email) {
        super("L'email fourni '" + email +"' n'est pas un format d'email valide.");
    }
}
