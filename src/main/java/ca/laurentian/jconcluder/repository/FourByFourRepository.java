package ca.laurentian.jconcluder.repository;

import ca.laurentian.jconcluder.model.FourByFour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FourByFourRepository extends JpaRepository<FourByFour, Long> {
}