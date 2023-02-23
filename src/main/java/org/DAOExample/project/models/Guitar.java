package org.DAOExample.project.models;

import org.DAOExample.project.DAO.GuitarDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class Guitar {
    private int id;
    private String name;


    public Guitar() {
    }

    public Guitar(int id, String name) {
        this.id = id;
        this.name = name;
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
}
