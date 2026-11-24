package fr.jrlelo.bnaiss.declarations;

public class DeclarationNotFoundException extends RuntimeException{
    private String message;

    public DeclarationNotFoundException(String message) {
    super(message);
    }
}
