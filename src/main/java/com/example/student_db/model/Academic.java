package com.example.student_db.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Academic implements PersonInterface{
    private final UUID id;
    @NotBlank
    private final String name;

    private final String gender;
    private final int age;
    private final String email;

    public Academic(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("gender") String gender, int age, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }



    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
