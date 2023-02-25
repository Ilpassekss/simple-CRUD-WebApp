package org.DAOExample.project.models;

import org.DAOExample.project.DAO.GuitarDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Guitar {

    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Guitar name should be more than 2 and less than 50 characters")
    private String name;
    @Min(value = 0, message = "Guitar age should be more than 0")
    private int age;
    @NotEmpty(message = "Guitar developer should not be empty")
    @Email(message = "email should be valid")
    private String developerEmail;

    public Guitar() {
    }

    public Guitar(int id, String name, int age, String developerEmail) {
        this.id = id;
        this.name = name;
        this.age=age;
        this.developerEmail=developerEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDeveloperEmail() {
        return developerEmail;
    }

    public void setDeveloperEmail(String developerEmail) {
        this.developerEmail = developerEmail;
    }
}
