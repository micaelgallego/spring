package es.urjc.code.daw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.urjc.code.daw.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("select t from Team t where t.name = ?1")
	List<Team> findByName(String name);
	
}