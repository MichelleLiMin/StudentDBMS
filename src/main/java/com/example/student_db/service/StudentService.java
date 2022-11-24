package com.example.student_db.service;

import com.example.student_db.dao.StudentDao;
import com.example.student_db.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.el.PropertyNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("postgres") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int insertStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    public List<Student> getAllPeople() {
        return studentDao.selectAllPeople();
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentDao.selectStudentById(id);
    }

    public int deleteStudent(UUID id) {
        return studentDao.deleteStudentById(id);
    }

    public int updateStudent(UUID id, Student newStudent) {
        return studentDao.updateStudentById(id, newStudent);
    }
}
