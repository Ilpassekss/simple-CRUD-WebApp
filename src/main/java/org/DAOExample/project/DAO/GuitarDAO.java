package org.DAOExample.project.DAO;

import org.DAOExample.project.models.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class GuitarDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuitarDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Guitar> guitars;

    public List<Guitar> index(){
        //here I use BeanPropertyRowMapper because here i don`t have any complicated logic
        return jdbcTemplate.query("select * from guitar ", new BeanPropertyRowMapper<>(Guitar.class));
    }
    //here i make almost all what i have done in index function, but return guitar object by id
    public Guitar show(int id) {
        //i don`t know why, but BeanPropertyRowMapper don`t work here and web page do not show developer mail when i use BeanPropertyRowMapper
        //so i wrote GuitarMapper
        return jdbcTemplate.query("select * from guitar where id=?", new Object[]{id},
                new GuitarMapper()).stream().findAny().orElse(null);
    }

    public void save(Guitar guitar) {
        //i have to fix problem with id generation
        jdbcTemplate.update("INSERT INTO guitar (name, age, developer_mail) values (? , ?, ?)",
                guitar.getName(), guitar.getAge(), guitar.getDeveloperEmail());
    }

    public void update(int id, Guitar updatedGuitar) {
        jdbcTemplate.update("update guitar set name = ? , age =? , " +
                "developer_mail = ? where id=?", updatedGuitar.getName(), updatedGuitar.getAge(), updatedGuitar.getDeveloperEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from guitar where id=?", id);
    }
}
