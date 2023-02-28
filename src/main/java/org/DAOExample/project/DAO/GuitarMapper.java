package org.DAOExample.project.DAO;

import org.DAOExample.project.models.Guitar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuitarMapper implements RowMapper<Guitar> {

    @Override
    public Guitar mapRow(ResultSet resultSet, int i) throws SQLException {
        Guitar guitar = new Guitar();

        guitar.setId(resultSet.getInt("id"));
        guitar.setName(resultSet.getString("name"));
        guitar.setAge(resultSet.getInt("age"));
        guitar.setDeveloperEmail(resultSet.getString("developer_mail"));
        return guitar;
    }
}
