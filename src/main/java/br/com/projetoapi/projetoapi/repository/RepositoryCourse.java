package br.com.projetoapi.projetoapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoapi.projetoapi.models.Course;

@Repository
public interface RepositoryCourse extends CrudRepository<Course, Long> {
    
    Iterable<Course> findByTitleContainingIgnoreCase(String title);
    Iterable<Course> findByOrderByTitle();
    int countById(Long courseId);
}
