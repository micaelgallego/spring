package es.urjc.code.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.daw.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
	
}