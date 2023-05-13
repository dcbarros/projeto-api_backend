package br.com.projetoapi.projetoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projetoapi.projetoapi.models.Course;
import br.com.projetoapi.projetoapi.models.StatusMessages;
import br.com.projetoapi.projetoapi.models.dto.CoursesDto;
import br.com.projetoapi.projetoapi.repository.RepositoryCourse;



@Service
public class CoursesServices {
    
    @Autowired
    private StatusMessages status;

    @Autowired
    private RepositoryCourse _repositoryCourses;

    public ResponseEntity<?> createNewCourse(CoursesDto request)
    {
        if(request.getTitle().isEmpty())
        {
            status.setMessages("O título do curso é obrigatório");
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        else if(request.getPrice() == null || request.getPrice() < 0)
        {
            status.setMessages("Insira um preço válido");
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>(_repositoryCourses.save(new Course(request)), HttpStatus.CREATED);
        }
    }
    
    public ResponseEntity<?> getAllCourses()
    {
        return new ResponseEntity<>(_repositoryCourses.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getCourseByCourseId(Long courseId)
    {
        if(_repositoryCourses.countById(courseId) == 0)
        {
            status.setMessages(String.format("O usuário de id: %d, não foi encontrado",courseId));
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>(_repositoryCourses.findById(courseId), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getCourseBySearchTitle(String search)
    {
        Iterable<Course> courses = _repositoryCourses.findByTitleContainingIgnoreCase(search);
        if(!courses.iterator().hasNext())
        {
            status.setMessages("Não foram encontrados cursos na plataforma com esse elemento de busca");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getAllCoursesSortedByTitle()
    {
        return new ResponseEntity<>(_repositoryCourses.findByOrderByTitle(), HttpStatus.OK);
    }

    public ResponseEntity<?> countCourses()
    {
        return new ResponseEntity<>(_repositoryCourses.count(), HttpStatus.OK);
    }

    public ResponseEntity<?> updateCourse(Long courseId, CoursesDto request) {
        Optional<Course> courseInRepository = _repositoryCourses.findById(courseId);
        if (!courseInRepository.isPresent()) {
            status.setMessages("Id do curso não encontrado");
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        
        Course course = courseInRepository.get();
        
        if (request.getTitle().isEmpty()) {
            request.setTitle(course.getTitle());
        }
        
        if (request.getPrice() == null || request.getPrice() < 0) {
            request.setPrice(course.getPrice());
        }
        
        if (request.getDescription().isEmpty()) {
            request.setDescription(course.getDescription());
        }
        
        course.setTitle(request.getTitle());
        course.setPrice(request.getPrice());
        course.setDescription(request.getDescription());
        
        Course updatedCourse = _repositoryCourses.save(course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCourseById(Long courseId)
    {
        Optional<Course> courseInRepository = _repositoryCourses.findById(courseId);
        if(courseInRepository == null)
        {
            status.setMessages("Id do curso não encontrado");
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        else
        {
            _repositoryCourses.deleteById(courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
