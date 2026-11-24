package fr.jrlelo.bnaiss.declarations;

import fr.jrlelo.bnaiss.profiles.ProfilesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@Component
public class DeclarationsMapper {
    private final ProfilesMapper profilesMapper;

    public Declaration dtoToEntity(DeclarationDTO dto) {
        Declaration entity = new Declaration();
        entity.setChild(profilesMapper.dtoToEntity(dto.getChild()));
        entity.setFather(profilesMapper.dtoToEntity(dto.getFirstParent()));
        entity.setMother(profilesMapper.dtoToEntity(dto.getSecondParent()));
        entity.setCreation(LocalDateTime.now());
        return entity;
    }

    public DeclarationDTO entityToDTO(Declaration declaration) {
        return DeclarationDTO
                .builder()
                .id(declaration.getId())
                .creation(declaration.getCreation())
                .firstParent(profilesMapper.entityToDto(declaration.getFather()))
                .secondParent(profilesMapper.entityToDto(declaration.getMother()))
                .child(profilesMapper.entityToDto(declaration.getChild()))
                .build();
    }
}
