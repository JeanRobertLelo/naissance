package fr.jrlelo.bnaiss.profiles;

import fr.jrlelo.bnaiss.declarations.Declaration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;
    private String phone;
    private String password;

    // Les Authorities sont les roles de l'utilisateur.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
    //On peut faire aussi la relation bidirectionnelle, mais ce n'est pas intéressant
    /*@OneToMany(mappedBy = "father", cascade = CascadeType.ALL)
    private List<Declaration> fatherDeclarations;*/
}
