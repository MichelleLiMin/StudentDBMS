package com.example.student_db.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Department {
    private final String name;
    private final Student student;
    private final Academic academic;

    @Autowired
    public Department(String name, Student student, Academic academic) {
        this.name = name;
        this.student = student;
        this.academic = academic;
    }
    
}
