package es.urjc.code.daw;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.model.Project;
import es.urjc.code.daw.model.Student;

@RestController
public class SchoolController {

	@Autowired
	private ProjectRepository projectRepository;
	
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

	interface StudentView extends Student.BasicAtt, Student.ProjectAtt, Project.BasicAtt {}
	
	@JsonView(StudentView.class)
	@RequestMapping("/students/")
	public List<Student> getStudents() throws Exception {
		return studentRepository.findAll();
	}
	
	@JsonView(StudentView.class)
	@RequestMapping("/students/{id}")
	public Student getStudent(@PathVariable long id) throws Exception {
		return studentRepository.findOne(id);
	}
	
	interface ProjectView extends Project.BasicAtt, Project.StudentAtt, Student.BasicAtt {}
	
	@JsonView(ProjectView.class)
	@RequestMapping("/projects/")
	public List<Project> getProjects() throws Exception {
		return projectRepository.findAll();
	}
	
	@JsonView(ProjectView.class)
	@RequestMapping("/projects/{id}")
	public Project getProject(@PathVariable long id) throws Exception {
		return projectRepository.findOne(id);
	}	
}
