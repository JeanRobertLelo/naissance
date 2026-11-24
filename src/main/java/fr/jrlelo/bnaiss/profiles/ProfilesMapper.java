package fr.jrlelo.bnaiss.profiles;

import fr.jrlelo.bnaiss.security.authentication.SignupRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProfilesMapper {

    public Profile dtoToEntity(ProfileDTO dto) {
        Profile entity = new Profile();
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setActive(dto.isActive());
        return entity;
    }

    public Profile dtoToEntity(SignupRequestDTO dto) {
        Profile entity = new Profile();
        entity.setPassword(dto.password());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        return entity;
    }

    public ProfileDTO entityToDto(Profile entity) {
        ProfileDTO profileDTO = ProfileDTO
                .builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .active(entity.isActive())
                .build();
        return profileDTO;
    }
}
