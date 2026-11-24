package fr.jrlelo.bnaiss.profiles;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfilesService implements UserDetailsService {
    private final ProfileRepository profileRepository;
    private final ProfilesMapper profilesMapper;


    public ProfileDTO createProfile(ProfileDTO profileDto) {
        Profile profileEntity = new Profile();
        profileEntity.setFirstName(profileDto.getFirstName());
        profileEntity.setLastName(profileDto.getLastName());
        profileEntity.setPassword(profileDto.getPassword());
        profileEntity.setEmail(profileDto.getEmail());
        profileEntity.setPhone(profileDto.getPhone());
        profileEntity.setActive(profileDto.isActive());

        return profilesMapper.entityToDto(profileRepository.save(profileEntity));
    }

    @Override
    public Profile loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.profileRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
