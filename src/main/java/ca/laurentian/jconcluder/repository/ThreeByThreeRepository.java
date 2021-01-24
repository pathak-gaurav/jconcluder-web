package ca.laurentian.jconcluder.repository;

import ca.laurentian.jconcluder.model.ThreeByThree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThreeByThreeRepository extends JpaRepository<ThreeByThree, Long> {

    @Query("Select t from ThreeByThree t where t.nodeName =:nodeName")
    List<ThreeByThree> findByNode(@Param("nodeName") String nodeName);
}