package org.DAOExample.project.DAO;

import org.DAOExample.project.models.Guitar;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class GuitarDAO {

    private List<Guitar> guitars;

    private int GUITARS_COUNT ;
    private static final String URL = "jdbc:postgresql://localhost:5432/guitars_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "passekss";
    private static Connection connection;


    static {
        //driver class download and checking
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //get connection
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Guitar> index(){

       List<Guitar> guitarList = new ArrayList<>();

        try {
            //object in which I collect date from database by url request
            Statement statement = connection.createStatement();

            //sql code
            String SQL = "select * from guitar";
            //statement.executeQuery(SQL); returns Result set object, which collect all data from table
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Guitar guitar = new Guitar();
                //get data from resultSet into guitar obj
                guitar.setId(resultSet.getInt("id"));
                guitar.setName(resultSet.getString("name"));
                guitar.setAge(resultSet.getInt("age"));
                guitar.setDeveloperEmail(resultSet.getString("developer_mail"));

                GUITARS_COUNT ++;

                guitarList.add(guitar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return guitarList;
    }

    public Guitar show(int id) {

        Guitar guitar = null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * from guitar where id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            guitar = new Guitar();

            guitar.setId(resultSet.getInt("id"));
            guitar.setName(resultSet.getString("name"));
            guitar.setAge(resultSet.getInt("age"));
            guitar.setDeveloperEmail(resultSet.getString("developer_mail"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guitar;
    }

    public void save(Guitar guitar) {

        try {
            int id = ++GUITARS_COUNT;

            //this way excludes the possibility of changing the table through the html form also this way faster
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO guitar values (id, ? , ?, ?)");//i have to fix this problem with id

            preparedStatement.setString(1, guitar.getName());
            preparedStatement.setInt(2, guitar.getAge());
            preparedStatement.setString(3, guitar.getDeveloperEmail());


            //add data to database
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(int id, Guitar updatedGuitar) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update guitar set name = ? , age =? , " +
                    "developer_mail = ? where id=?");

            preparedStatement.setString(1, updatedGuitar.getName());
            preparedStatement.setInt(2, updatedGuitar.getAge());
            preparedStatement.setString(3, updatedGuitar.getDeveloperEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from guitar where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
