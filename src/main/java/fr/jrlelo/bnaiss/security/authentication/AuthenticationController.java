package fr.jrlelo.bnaiss.security.authentication;

import fr.jrlelo.bnaiss.profiles.ProfilReponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/sign-up", consumes = "application/json")
    public ResponseEntity<ProfilReponseDTO> signup(
            @RequestBody SignupRequestDTO signupRequestDTO) {
        ProfilReponseDTO savedProfile = authenticationService.signup(signupRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @PostMapping(value = "/activate", consumes = "application/json")
    public ResponseEntity<ProfilReponseDTO> activateAccount(
            @RequestBody Map<String, String> signupRequesParams) {
        ProfilReponseDTO savedProfile = authenticationService.active(signupRequesParams);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }
}
