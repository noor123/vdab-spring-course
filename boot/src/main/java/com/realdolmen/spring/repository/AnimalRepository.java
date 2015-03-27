package com.realdolmen.spring.repository;

import com.realdolmen.spring.domain.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class AnimalRepository {
    @Autowired private JdbcTemplate jdbcTemplate;

    public List<Animal> findAll() {
        return jdbcTemplate.query("select id, name, type from animals", (rs, i) -> {
            return new Animal(rs.getString("name"), Animal.Type.valueOf(rs.getString("type")));
        } );
    }

    public void create(Animal animal) {
        jdbcTemplate.update("insert into animals(name, type) values(?, ?)", animal.getName(), animal.getType().name());
    }
}
