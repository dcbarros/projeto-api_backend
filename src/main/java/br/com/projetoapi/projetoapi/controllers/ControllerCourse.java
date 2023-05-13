package br.com.projetoapi.projetoapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoapi.projetoapi.models.dto.CoursesDto;
import br.com.projetoapi.projetoapi.services.CoursesServices;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*") //Diz a porta que o back será acessado
public class ControllerCourse {
    
    @Autowired
    private CoursesServices _coursesServices;


    @PostMapping("/")
    public ResponseEntity<?> CreateCourse(@RequestBody CoursesDto request)
    {
        return _coursesServices.createNewCourse(request);
    }
    
    @GetMapping("/")
    public ResponseEntity<?> getAllCourses()
    {
        return _coursesServices.getAllCourses();
    }

    @GetMapping(path="/{courseId}")
    public ResponseEntity<?> getCourseByCourseId(@PathVariable Long courseId)
    {
        return _coursesServices.getCourseByCourseId(courseId);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> getCoursesByTitle(@RequestParam(name="title") String title)
    {
        return _coursesServices.getCourseBySearchTitle(title);
    }

    @GetMapping("/sortByTitle")
    public ResponseEntity<?> getSortCoursesByTitle()
    {
        return _coursesServices.getAllCoursesSortedByTitle();
    }

    @GetMapping("/count")
    public ResponseEntity<?> countCourses()
    {
        return _coursesServices.countCourses();
    }

    @PutMapping(path="/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody CoursesDto request)
    {
        return _coursesServices.updateCourse(courseId, request);
    }
    
    @DeleteMapping(path="/{courseId}")
    public ResponseEntity<?> removeCourse(@PathVariable Long courseId)
    {
        return _coursesServices.deleteCourseById(courseId);
    }
}
