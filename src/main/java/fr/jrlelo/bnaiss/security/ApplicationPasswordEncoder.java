package fr.jrlelo.bnaiss.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Cette classe permet d'encoder tous les mot de passe de l'application
// Comme j'aurai le mot de passe des utilisaateurs, je ne dois pas le stocker en clair dans la base de données
@Configuration
public class ApplicationPasswordEncoder {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
