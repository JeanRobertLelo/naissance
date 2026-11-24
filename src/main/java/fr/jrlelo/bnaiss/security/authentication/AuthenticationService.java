package fr.jrlelo.bnaiss.security.authentication;

import fr.jrlelo.bnaiss.profiles.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfilesMapper profilesMapper;

    public ProfilReponseDTO signup(ProfileDTO profileDTO) {
        profileRepository.findByEmail(profileDTO.getEmail())
                .ifPresent(existingProfile -> {
                    throw new EmailAlreadyExistsException();
                });

        Profile profile = profilesMapper.dtoToEntity(profileDTO);
        profile.setPassword(passwordEncoder.encode(profileDTO.getPassword()));
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
