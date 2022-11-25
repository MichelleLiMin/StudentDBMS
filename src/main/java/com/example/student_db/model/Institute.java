package com.example.student_db.model;


import org.springframework.beans.factory.annotation.Autowired;

public class Institute {
    private final String name;

    private final Department department;
@Autowired
    public Institute(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }


}
