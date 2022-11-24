package com.example.student_db.api;

import com.example.student_db.model.Student;
import com.example.student_db.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void insertStudent(@NonNull @Valid @RequestBody Student student) {
        studentService.insertStudent(student);
    }

    @GetMapping
    public List<Student> getAllPeople() {
        return studentService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Student getStudentById(@PathVariable("id") UUID id) {
        return studentService.getStudentById(id)
                .orElse(null);}

    @DeleteMapping(path = "{id}")
    public void deleteStudentById(@PathVariable("id") UUID id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{id}")
    public void updateStudent(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Student studentToUpdate) {
        studentService.updateStudent(id, studentToUpdate);
    }
}
