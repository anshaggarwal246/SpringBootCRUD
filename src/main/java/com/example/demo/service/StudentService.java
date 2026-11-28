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

        //by default it should be false no matter if someone passes it in body or not
        studentReq.setDeleted(false);
        Student Resp = studentRepository.save(studentReq);
        return Resp;

    }

    public Optional<Student> getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);

        return studentResp;
    }

    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList;

    }

    public Student updateStudent(Long id, Student student){
         Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

         if(existingStudent.isEmpty()) return null;

         Student studentToSave = existingStudent.get();
         studentToSave.setName(student.getName());
         studentToSave.setRollNo(student.getRollNo());
         studentToSave.setEmail(student.getEmail());
         studentToSave.setAge(student.getAge());
         studentToSave.setSubject(student.getSubject());

         //doesnt really matter
        studentToSave.setDeleted(false);

         return studentRepository.save(studentToSave);
    }

    public boolean deleteStudent(Long id){
        Boolean isStudent = studentRepository.existsById(id);

        if(!isStudent) return false;

        studentRepository.deleteById(id);
        return true;
    }

    public boolean deleteStudentSoft(Long id){
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) return false;

        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);

        return true;
    }

}
