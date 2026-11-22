package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {

    public Student saveStudent(Student student){
        //save to db
        Student s1 = new Student();

        s1.setId(1L);
        s1.setName("Ansh");
        s1.setAge(20);
        s1.setEmail("ansh@gmail.com");
        s1.setRollNo(5);
        s1.setSubject("Spring Boot");

        return s1;
    }

}
