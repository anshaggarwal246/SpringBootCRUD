package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//jpa will creates its obj and it will be managed by spring
//@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
