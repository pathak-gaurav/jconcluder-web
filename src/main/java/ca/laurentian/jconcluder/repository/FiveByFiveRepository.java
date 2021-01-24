package ca.laurentian.jconcluder.repository;

import ca.laurentian.jconcluder.model.FiveByFive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiveByFiveRepository extends JpaRepository<FiveByFive, Long> {
}