package es.urjc.code.daw;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.daw.model.Project;
import es.urjc.code.daw.model.Student;

@RestController
public class SchoolController {
	
	@Autowired
	private StudentRepository studentRepository;

	@PostConstruct
	public void init() {

		Student s1 = new Student("Pepe", 2010);
		s1.setProject(new Project("TFG1", 8));
		studentRepository.save(s1);
		
		Student s2 = new Student("Juan", 2011);
		studentRepository.save(s2);		
	}

	@RequestMapping("/students/")
	public List<Student> getStudents() throws Exception {
		return studentRepository.findAll();
	}
}
