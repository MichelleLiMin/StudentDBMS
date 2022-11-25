package com.example.student_db.dao;

import com.example.student_db.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class StudentDataAccessService implements StudentDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertStudent(UUID id, Student student) {

        try {
            final String sql = "INSERT INTO student(id, name, gender, age, email) VALUES (?, ?, ?, ?, ?);";

            jdbcTemplate.update(sql, id, student.getName(), student.getGender(), student.getAge(), student.getEmail());
            return 1;
        } catch (Exception e) {
            System.out.println(e);

            System.out.println("Something went wrong, this student cannot be inserted");
            return 0;
        }

    }

    @Override
    public List<Student> selectAllStudent() {
        final String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            String gender =rs.getString("gender");
            int age = Integer.parseInt(rs.getString("age"));
            String email =rs.getString("email");

            return new Student(id, name, gender, age, email);

        });
    }

    @Override
    public Optional<Student> selectStudentById(UUID id) {
        final String sql = "SELECT id, name, gender, age, email FROM student WHERE id = ?";


        List<Student> student = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    UUID studentId = UUID.fromString(rs.getString("id"));
                    String name = rs.getString("name");
                    String gender = rs.getString("gender");
                    int age = Integer.parseInt(rs.getString("age"));
                    String email = rs.getString("email");

                    return new Student(studentId, name, gender, age, email);
                },
                id);

        System.out.println("before return"+ Optional.ofNullable(student));
        return student.stream().findFirst();

    }

    @Override
    public int deleteStudentById(UUID id) {
        final String sql = "DELETE FROM student WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("I am sorry, This student cannot be deleted");
            return 0;
        }
    }

    @Override
    public int updateStudentById(UUID id, Student student) {
        final String sql = "UPDATE student SET name = ?, gender = ?, age = ?, email = ? WHERE id = ?";

        try {
            System.out.println("before update");
            jdbcTemplate.update(sql, student.getName(), student.getGender(), student.getAge(), student.getEmail(), id);
            return 1;
        } catch (Exception e) {
             System.out.println(e);
            return 0;
        }
    }
}
