package es.urjc.code.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.daw.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
}