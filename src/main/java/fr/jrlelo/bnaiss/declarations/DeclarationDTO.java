package fr.jrlelo.bnaiss.declarations;

import fr.jrlelo.bnaiss.profiles.ProfileDTO;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeclarationDTO {
    private int id;
    private LocalDateTime creation;
    private ProfileDTO firstParent;
    private ProfileDTO secondParent;
    private ProfileDTO child;
}
