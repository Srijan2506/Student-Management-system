package com.example.SpringBoot.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBoot.Entity.Student;
import com.example.SpringBoot.Repository.StudentRepo;


@Service
public class DatabaseService {

    @Autowired
    private StudentRepo studentRepo;

    public void create(Student student) {
        studentRepo.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepo.findById(id).orElse(null);
    }

    public void update(Student student, Integer id) {
        Student existingStudent = studentRepo.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setRollNo(student.getRollNo());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setFatherName(student.getFatherName());
            existingStudent.setComment(student.getComment());
            studentRepo.save(existingStudent);
        }
    }
}
