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

                GUITARS_COUNT = resultSet.getInt("id");

                guitarList.add(guitar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return guitarList;
    }

    public Guitar show(int id){
        //return guitars.stream().filter(guitar->guitar.getId() == id ).findAny().orElse(null);
        return null;
    }


    public void save(Guitar guitar) {
       // guitar.setId(++GUITARS_COUNT);
       // guitars.add(guitar);

        try {


            Statement statement = connection.createStatement();
            int id = ++GUITARS_COUNT;

            String SQL = "insert into Guitar values("+id+",'"+guitar.getName()+"',"+guitar.getAge()+",'"
                    +guitar.getDeveloperEmail()+"')";
            //add data to database
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(int id, Guitar updatedGuitar) {
        Guitar guitarToBeUpdated = show(id);
        guitarToBeUpdated.setName(updatedGuitar.getName());
        guitarToBeUpdated.setAge(updatedGuitar.getAge());
        guitarToBeUpdated.setDeveloperEmail(updatedGuitar.getDeveloperEmail());
    }

    public void delete(int id) {
        guitars.removeIf(g->g.getId()==id);
    }
}
