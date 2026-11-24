package fr.jrlelo.bnaiss.profiles;

public record ProfilReponseDTO(
    int id,
    String firstName,
    String lastName,
    String email,
    String phone,
    boolean active
    ) {}
