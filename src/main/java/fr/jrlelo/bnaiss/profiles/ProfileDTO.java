package fr.jrlelo.bnaiss.profiles;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProfileDTO {
    String firstName;
    String lastName;
    String email;
    String phone;
    String password;
    boolean active;
}
