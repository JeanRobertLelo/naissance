package fr.jrlelo.bnaiss.security.authentication;

public record SignupRequestDTO(
        String firstName,
        String lastName,
        String email,
        String phone,
        String password) {
}
