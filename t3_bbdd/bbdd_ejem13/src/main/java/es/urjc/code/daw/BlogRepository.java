package es.urjc.code.daw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import es.urjc.code.daw.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>, QueryDslPredicateExecutor<Blog> {
	
	

}