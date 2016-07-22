package es.urjc.code.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.daw.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
	
}