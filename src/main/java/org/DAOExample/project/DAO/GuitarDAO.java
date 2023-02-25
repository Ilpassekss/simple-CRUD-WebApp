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
        guitars.add(new Guitar(++GUITARS_COUNT, "Gibson LesPaul", 1, "gibson@gmail.com"));
        guitars.add(new Guitar(++GUITARS_COUNT, "Fender Stratocaster", 40, "fender@mail.com"));
        guitars.add(new Guitar(++GUITARS_COUNT, "Gibson SG", 5, "gibson@gmail.com"));
        guitars.add(new Guitar(++GUITARS_COUNT, "Gibson Explorer", 2, "gibson@gmail.com"));
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
