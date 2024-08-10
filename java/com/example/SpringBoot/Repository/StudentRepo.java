package com.example.srijanshukla.SpringBoot.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBoot.Entity.Student;



@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}



