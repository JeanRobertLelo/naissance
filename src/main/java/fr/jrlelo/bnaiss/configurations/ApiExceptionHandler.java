package fr.jrlelo.bnaiss.configurations;

import fr.jrlelo.bnaiss.security.authentication.EmailAlreadyExistsException;
import fr.jrlelo.bnaiss.shared.activations.CodeActivationInvalideException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailAlreadyExists(HttpServletRequest request) {
        return problem(
                HttpStatus.CONFLICT,
                "Adresse e-mail déjà utilisée",
                "Un compte existe déjà avec cette adresse e-mail.",
                "EMAIL_ALREADY_EXISTS",
                request
        );
    }

    @ExceptionHandler(CodeActivationInvalideException.class)
    public ProblemDetail handleInvalidActivationCode(HttpServletRequest request) {
        return problem(
                HttpStatus.BAD_REQUEST,
                "Code d’activation invalide",
                "Ce code est invalide, expiré ou déjà utilisé.",
                "INVALID_ACTIVATION_CODE",
                request
        );
    }

    private ProblemDetail problem(
            HttpStatus status,
            String title,
            String detail,
            String code,
            HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("code", code);
        return problem;
    }
}

