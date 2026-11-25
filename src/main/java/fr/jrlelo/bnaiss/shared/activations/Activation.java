package fr.jrlelo.bnaiss.shared.activations;

import fr.jrlelo.bnaiss.profiles.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "activations")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @Transient
    private String userCode;
    private boolean active;
    private LocalDateTime creation;
    private LocalDateTime desactivation;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "profile_id")
    private Profile profileId;
}
