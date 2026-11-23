package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        //business logic
        Student Resp = studentRepository.save(studentReq);
        return Resp;

    }

    public Optional<Student> getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findById(id);

        return studentResp;
    }

    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        return studentList;

    }

    public Student updateStudent(Long id, Student student){
         Optional<Student> existingStudent = studentRepository.findById(id);

         if(existingStudent.isEmpty()) return null;

         Student studentToSave = existingStudent.get();
         studentToSave.setName(student.getName());
         studentToSave.setRollNo(student.getRollNo());
         studentToSave.setEmail(student.getEmail());
         studentToSave.setAge(student.getAge());
         studentToSave.setSubject(student.getSubject());

         return studentRepository.save(studentToSave);
    }
}
