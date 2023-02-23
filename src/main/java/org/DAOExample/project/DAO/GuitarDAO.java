package org.DAOExample.project.DAO;

import org.DAOExample.project.models.Guitar;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class GuitarDAO {

    private List<Guitar> guitars;
    private int GUITARS_COUNT ;
    {
        guitars = new ArrayList<>();
        guitars.add(new Guitar(++GUITARS_COUNT, "Gibson LesPaul"));
        guitars.add(new Guitar(++GUITARS_COUNT, "Fender Stratocaster"));
        guitars.add(new Guitar(++GUITARS_COUNT, "Gibson SG"));
        guitars.add(new Guitar(++GUITARS_COUNT, "Gibson Explorer"));
    }

    public List<Guitar> index(){
        return guitars;
    }

    public Guitar show(int id){
        return guitars.stream().filter(guitar->guitar.getId() == id ).findAny().orElse(null);
    }


    public void save(Guitar guitar) {
        guitar.setId(++GUITARS_COUNT);
        guitars.add(guitar);

    }
}
