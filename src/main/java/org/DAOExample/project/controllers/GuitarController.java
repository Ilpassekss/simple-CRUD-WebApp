package org.DAOExample.project.controllers;


import org.DAOExample.project.DAO.GuitarDAO;
import org.DAOExample.project.models.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/guitarsShop")
public class GuitarController {

    private GuitarDAO guitarDAO;

    @Autowired
    public GuitarController(GuitarDAO guitarDAO) {
        this.guitarDAO = guitarDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("guitars", guitarDAO.index());
        return "guitars/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("guitar", guitarDAO.show(id));
        return "guitars/show";
    }
    @GetMapping("/new")
    public String newGuitar(Model model){
        model.addAttribute("guitar", new Guitar());
        return "guitars/new";
    }

    @PostMapping()
    public String createGuitar(@ModelAttribute Guitar guitar){
        guitarDAO.save(guitar);
        return "redirect:/guitarsShop";
    }

    //guitar edit method
    @GetMapping("/{id}/editPage")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("guitar", guitarDAO.show(id));
        return "guitars/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("guitar") Guitar guitar, @PathVariable("id")int id ){
        guitarDAO.update(id , guitar);
        return "redirect:/guitarsShop";
    }

    @DeleteMapping("/{id}")
    public String deleteGuitar(@PathVariable("id")int id){
        guitarDAO.delete(id);
        return "redirect:/guitarsShop";
    }
}
