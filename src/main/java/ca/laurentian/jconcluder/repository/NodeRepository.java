package ca.laurentian.jconcluder.repository;

import ca.laurentian.jconcluder.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Long> {
}
