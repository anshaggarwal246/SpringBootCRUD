package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    //two ways to getting anything -> path varibale and query parameters
    //but the thing in request para is that the variable name sending and receiving should be same

    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id) {
        Optional<Student> studentResp = studentService.getStudent(id);

        if (!studentResp.isPresent()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(studentResp.get());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentService.getAllStudent();

        if (studentList.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentList);

    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id,
                                                 @RequestBody Student student) {
        Student studentResp = studentService.updateStudent(id, student);
        return ResponseEntity.ok(studentResp);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        Boolean isDeleted = studentService.deleteStudent(id);

        if(isDeleted) return ResponseEntity.ok("DELETED");
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoft(@RequestParam Long id)
    {
        Boolean isDeleted = studentService.deleteStudentSoft(id);

        if(!isDeleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Record Deleted");
    }
}
