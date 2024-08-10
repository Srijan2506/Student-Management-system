package com.example.srijanshukla.SpringBoot.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.SpringBoot.Entity.Student;
import com.example.SpringBoot.Service.DatabaseService;



@RestController
public class StudentController {

    @Autowired
    private DatabaseService databaseService;

    // Mapping to show the student form
    @GetMapping("/studentForm")
    public ModelAndView showStudentForm(@RequestParam(required = false) Integer id) {
        String viewName = "studentForm";
        Map<String, Object> model = new HashMap<>();
        
        if (id == null) {
            model.put("student", new Student());
        } else {
            model.put("student", databaseService.getStudentById(id));
        }
        
        return new ModelAndView(viewName, model);
    }

    // Handling form submission
    @PostMapping("/studentForm")
    public ModelAndView submitStudentForm(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("studentForm");
        }

        if (student.getId() == null) {
            databaseService.create(student); // Create new student
        } else {
            databaseService.update(student, student.getId()); // Update existing student
        }

        // Redirect to the students list page
        return new ModelAndView("redirect:/students");
    }

    // Mapping to display students list
    @GetMapping("/students")
    public ModelAndView getStudents() {
        String viewName = "students";
        Map<String, Object> model = new HashMap<>();
        List<Student> studentList = databaseService.getAllStudents();
        model.put("studentRows", studentList);
        model.put("noOfStudents", studentList.size());
        return new ModelAndView(viewName, model);
    }
}
