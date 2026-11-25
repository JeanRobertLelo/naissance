package fr.jrlelo.bnaiss.shared.activations;

import fr.jrlelo.bnaiss.profiles.Profile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ActivationService {
    private final ActivationRepository activationRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String activate(Profile profile) {
        // Génération d'un code alphanumérique de 6 caractères
        String code = RandomStringUtils.secure().nextNumeric(6);
        String code1 = RandomStringUtils.random(
                8,
                5000,
                9000,
                false,
                true
        );
        Activation activation = Activation
                .builder()
                .userCode(code)
                .code(this.passwordEncoder.encode(code))
                .creation(LocalDateTime.now())
                .desactivation(LocalDateTime.now().plusMinutes(5))
                .active(true)
                .profileId(profile)
                .build();
        this.activationRepository.save(activation);
        return code;
    }

}
