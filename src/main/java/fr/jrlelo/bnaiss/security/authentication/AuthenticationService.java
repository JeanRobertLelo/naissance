package fr.jrlelo.bnaiss.security.authentication;

import fr.jrlelo.bnaiss.profiles.ProfilReponseDTO;
import fr.jrlelo.bnaiss.profiles.Profile;
import fr.jrlelo.bnaiss.profiles.ProfileRepository;
import fr.jrlelo.bnaiss.profiles.ProfilesMapper;
import fr.jrlelo.bnaiss.shared.activations.ActivationService;
import fr.jrlelo.bnaiss.shared.activations.notifications.EmailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfilesMapper profilesMapper;
    private final ActivationService activationService;
    private final EmailsService emailsService;

    public ProfilReponseDTO signup(SignupRequestDTO signupRequestDTO) {
        profileRepository.findByEmail(signupRequestDTO.email())
                .ifPresent(existingProfile -> {
                    throw new EmailAlreadyExistsException();
                });

        Profile profile = profilesMapper.dtoToEntity(signupRequestDTO);
        profile.setPassword(passwordEncoder.encode(signupRequestDTO.password()));
        profile.setActive(false);


        Profile savedProfile = profileRepository.save(profile);
        String activatedCode = this.activationService.activate(savedProfile);
        emailsService.sendActivationEmail(
                Map.of(
                        "code", activatedCode,
                        "email", savedProfile.getEmail(),
                        "name", savedProfile.getFirstName() + " " + savedProfile.getLastName()
                )
        );

        return new ProfilReponseDTO(
                savedProfile.getId(),
                savedProfile.getFirstName(),
                savedProfile.getLastName(),
                savedProfile.getEmail(),
                savedProfile.getPhone(),
                savedProfile.isActive()
        );

    }
}
