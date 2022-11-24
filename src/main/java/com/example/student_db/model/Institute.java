package com.example.student_db.model;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Institute {
    private final String name;
    private final String department;

    public Institute(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

}
