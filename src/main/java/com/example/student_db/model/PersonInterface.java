package com.example.student_db.model;

import java.util.UUID;

public interface PersonInterface {
    public UUID getId();

    public String getName();

    public String getGender();

    public int getAge();

    public String getEmail();

}
