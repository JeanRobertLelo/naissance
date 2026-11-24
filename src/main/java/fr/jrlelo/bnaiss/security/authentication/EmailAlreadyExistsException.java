package fr.jrlelo.bnaiss.security.authentication;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Cette adresse e-mail est déjà utilisée.");
    }
}
