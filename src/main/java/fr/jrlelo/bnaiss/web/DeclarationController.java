package fr.jrlelo.bnaiss.web;


import fr.jrlelo.bnaiss.declarations.DeclarationDTO;
import fr.jrlelo.bnaiss.declarations.DeclarationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/declarations")
@AllArgsConstructor
public class DeclarationController {
    private final DeclarationsService declarationsService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeclarationDTO> createDeclaration(@RequestBody DeclarationDTO declarationDTO) {
        DeclarationDTO savedDeclaration =
                declarationsService.createDeclaration(declarationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDeclaration);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeclarationDTO>> getDeclaration() {
        List<DeclarationDTO> declaration = declarationsService.getAllDeclarations();
        return ResponseEntity.ok(declaration);
    }
}
