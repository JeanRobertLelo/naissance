package fr.jrlelo.bnaiss.declarations;


import fr.jrlelo.bnaiss.profiles.ProfileDTO;
import fr.jrlelo.bnaiss.profiles.ProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeclarationsService {
    private final DeclarationsRepository declarationsRepository;
    private final ProfilesService profilesService;
    private final DeclarationsMapper declarationsMapper;

    public DeclarationDTO getDeclarationById(Integer id) {
        Declaration entityDeclaration = declarationsRepository.findById(id).orElse(null);
        DeclarationDTO declarationDTO = null;
        if(!Objects.isNull(entityDeclaration)) {
            declarationDTO = declarationsMapper.entityToDTO(entityDeclaration);
        }
        return declarationDTO;
    }

    public DeclarationDTO createDeclaration(DeclarationDTO declaration) {
        Declaration declarationSaved = declarationsRepository.save(declarationsMapper.dtoToEntity(declaration));
        return getDeclarationDTO(declarationsMapper.entityToDTO(declarationSaved));
    }

    public DeclarationDTO updateDeclaration(int declarationId) {
        Declaration declarationDB = declarationsMapper.dtoToEntity(getDeclarationById(declarationId));
        return getDeclarationDTO(declarationsMapper.entityToDTO(declarationDB));
    }

    private DeclarationDTO getDeclarationDTO(DeclarationDTO declarationDB) {

        ProfileDTO childProfile = this.profilesService.createProfile(declarationDB.getChild());
        ProfileDTO fatherProfile = this.profilesService.createProfile(declarationDB.getFirstParent());
        ProfileDTO motherProfile = this.profilesService.createProfile(declarationDB.getSecondParent());

        declarationDB.setChild(childProfile);
        declarationDB.setFirstParent(fatherProfile);
        declarationDB.setSecondParent(motherProfile);

        return declarationsMapper.entityToDTO(declarationsRepository.save(declarationsMapper.dtoToEntity(declarationDB)));
    }

    public void deleteDeclaration(Integer id) {
        declarationsRepository.deleteById(id);
    }

    public List<DeclarationDTO> getAllDeclarations() {
        return declarationsRepository.findAll().stream().map(declarationsMapper::entityToDTO).toList();
    }
}
