package fr.jrlelo.bnaiss.shared.activations;

public class CodeActivationInvalideException extends RuntimeException {

    public CodeActivationInvalideException() {
        super("Le code d’activation est invalide ou expiré.");
    }
}
