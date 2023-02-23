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
}
