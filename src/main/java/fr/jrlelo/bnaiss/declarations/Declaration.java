package fr.jrlelo.bnaiss.declarations;

import fr.jrlelo.bnaiss.profiles.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "declarations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime creation;
    // un parent peur faire une declaration
    // une declaration peut être faite par plusieurs parents
    // Qu'est ce qui se passe quand on veut créer une déclaration : il faut qu'on ait deja créé le père en base de données
    // Si le père est déjà en base de données,comment on gère, car il a deja un identifant
    // la gestion de la création de la donnée en cascade , cad
    // 1 - quand on crée une déclaration, on doit créer le père en base de données
    // 2 - Si le père est déjà en base de données, on doit créer la déclaration en cascade (en cascade cad on doit faire la jointure avec l'identifiant du père)
    // 1 er cas de creation et de suppression de la declaration e : Créer declaration et créer le père en base de données et si on supprime la declaration on supprime la personne
    // 1 er Cas : @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    // 2 ème cas de creation et de la suppression du père qui existedéjà en base

    // creation des données en cascade , cas de creation : PERSIST, MERGE
    // creation des données en cascade , cas de suppression : REMOVE, DETACH

    // Cascade = CascadeType.ALL est à prohiber : c'est une mauvaise pratique,
    // ça veut dire fait ce que tu veux ça me va

    // PERSIST : Quand on enregistre la declaration, on enregistre aussi le père
    // REMOVE : Quand on supprime la déclaration, on supprime aussi le père


    // MERGE : Quand on crée la declaration et comme le père existe déjà en base de données, on le fusionne
    // DETACH : Quand on supprime la déclaration, on laisse le père (car il était déjà dans la base)


    // Créer declaration et utiliser le père
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "father_id")
    private Profile father;

    // Créer declaration et utiliser la mère
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "mother_id")
    private Profile mother;

    // Créer declaration et utiliser l'enfant
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "child_id")
    private Profile child;

}
