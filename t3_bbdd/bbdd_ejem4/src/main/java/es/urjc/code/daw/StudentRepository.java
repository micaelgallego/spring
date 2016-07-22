package es.urjc.code.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.daw.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
}