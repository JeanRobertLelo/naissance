package fr.jrlelo.bnaiss.shared.activations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivationRepository extends JpaRepository<Activation, Integer> {
    List<Activation> findAllByActiveAndDesactivationAfter(boolean active, LocalDateTime date);
}
