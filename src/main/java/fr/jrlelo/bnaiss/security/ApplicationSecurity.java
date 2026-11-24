package fr.jrlelo.bnaiss.security;

import fr.jrlelo.bnaiss.profiles.ProfilesService;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class ApplicationSecurity {
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfilesService profilesService;

    @Bean
    // cors(cross origin request service : Si vous avez deux domaines avec de numero de port différent, ils
    // ne doivent pas s'appeler. Il faut dire à Spring security quel domaine doit appeler votre api)
    // la configuration par defaut, Spring sait deja le faire donc on laisse par defaut
    // csrf (cross site request forgery) : tous les domaines peuvent m'appeler exemple mesnaisannces.com et microservice.mesnaisannces.com
    // headers : Desactive moi les contrôles des en-tête'
    // Je vais dire à Spring que mes requêtes sont sans etat(ne stocke pas la session de la personne quand sa requête arrive)
    // Pas besoin de mémoriser, chaque appel est une nouvelle session
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        configurations ->
                                configurations
                                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                                        .requestMatchers(POST, "/activation").permitAll()
                                        .requestMatchers(POST, "/sign-up").permitAll()
                                        .requestMatchers(POST, "/sign-in").permitAll()
                                        .anyRequest().authenticated()
                )
                .sessionManagement(
                        session ->
                                session.sessionCreationPolicy(STATELESS)
                )
                .build();
    }

    //Scenario d'authentification
    // 1 Post  utilisateur (email, pwd)
    // Spring Security intercepte la requête en premier avant le controleur
    // 2 Spring Security va chercher dans l 'AM(Authentication Manager méthode authenticateManager : fichier, BDD, ou google (OAuth2) puis appelle le provider la méthode authenticateProvider
    // 3 Si l'utilisateur existe par son mail en BDD, on prend son pwd et compare avec celui qu'il a fourni
    // 4 Si l'utilisateur n'existe pas, on renvoie une erreur 401


    @Bean
    AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }

    //Quand l'utilisateur donnera son login et son mot de passe, Spring doit savoir où le chercher
    //
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(this.profilesService);
        provider.setPasswordEncoder(this.passwordEncoder);
        return provider;
    }
}
