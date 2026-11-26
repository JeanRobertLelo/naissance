package fr.jrlelo.bnaiss.shared.activations;

import fr.jrlelo.bnaiss.profiles.Profile;
import fr.jrlelo.bnaiss.profiles.ProfilesMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ActivationService {
    private final ActivationRepository activationRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfilesMapper profilesMapper;

    public String activate(Profile profile) {
        // Génération d'un code alphanumérique de 6 caractères
        String code = RandomStringUtils.secure().nextNumeric(6);

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


    public Profile validate(Map<String, String> signupRequesParams) {

        List<Activation> activations = this.activationRepository.findAllByActiveAndDesactivationAfter(true, LocalDateTime.now());

        String codeSaisi = signupRequesParams.get("code");

        if (codeSaisi == null || !codeSaisi.matches("[0-9]{6}")) {
            throw new CodeActivationInvalideException();
        }

        Activation activation = activations.stream()
                .filter(candidate -> passwordEncoder.matches(codeSaisi, candidate.getCode()))
                .findFirst()
                .orElseThrow(CodeActivationInvalideException::new);

        activation.setActive(false);
        Activation currentActivation = this.activationRepository.save(activation);

        return currentActivation.getProfileId();
    }
}
