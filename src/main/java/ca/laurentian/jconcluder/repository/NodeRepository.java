package ca.laurentian.jconcluder.repository;

import ca.laurentian.jconcluder.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long> {

    @Query("Select n from Node n where n.parentNode =:parentNode")
    List<Node> findByParentNode(@Param("parentNode")String parentNode);
}
