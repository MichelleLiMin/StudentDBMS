package com.example.student_db.dao;

import com.example.student_db.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {

        try {
            final String sql = "INSERT INTO person(id, name) VALUES (?, ?);";

            jdbcTemplate.update(sql, id, person.getName());
            return 1;
        } catch (Exception e) {
            System.out.println("Something went wrong, this person cannot be inserted");
            return 0;
        }

    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            return new Person(id, name);

        });
    };

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    UUID personId = UUID.fromString(rs.getString("id"));
                    String name = rs.getString("name");
                    return new Person(personId, name);
                },
                new Object[] { id });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM person WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
            return 1;
        } catch (Exception e) {
            System.out.println("I am sorry, This person cannot be deleted");
            return 0;
        }
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql = "UPDATE person SET name = ? WHERE id = ?";

        try {
            jdbcTemplate.update(sql, person.getName(), id);
            return 1;
        } catch (Exception e) {
            // System.out.println(e);
            return 0;
        }
    }
}
