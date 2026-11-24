package fr.jrlelo.bnaiss.security.authentication;

import fr.jrlelo.bnaiss.profiles.ProfilReponseDTO;
import fr.jrlelo.bnaiss.profiles.Profile;
import fr.jrlelo.bnaiss.profiles.ProfileRepository;
import fr.jrlelo.bnaiss.profiles.ProfilesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfilesMapper profilesMapper;

    public ProfilReponseDTO signup(SignupRequestDTO signupRequestDTO) {
        profileRepository.findByEmail(signupRequestDTO.email())
                .ifPresent(existingProfile -> {
                    throw new EmailAlreadyExistsException();
                });

        Profile profile = profilesMapper.dtoToEntity(signupRequestDTO);
        profile.setPassword(passwordEncoder.encode(signupRequestDTO.password()));
        profile.setActive(false);

        Profile savedProfile = profileRepository.save(profile);

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
