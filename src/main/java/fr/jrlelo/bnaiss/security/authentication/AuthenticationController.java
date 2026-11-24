package fr.jrlelo.bnaiss.security.authentication;

import fr.jrlelo.bnaiss.profiles.ProfilReponseDTO;
import fr.jrlelo.bnaiss.profiles.Profile;
import fr.jrlelo.bnaiss.profiles.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<ProfilReponseDTO> signup(
            @RequestBody ProfileDTO profileDTO) {
        ProfilReponseDTO savedProfile = authenticationService.signup(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }
}
