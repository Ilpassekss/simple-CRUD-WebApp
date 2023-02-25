package org.DAOExample.project.controllers;


import org.DAOExample.project.DAO.GuitarDAO;
import org.DAOExample.project.models.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String createGuitar(@ModelAttribute("guitar") @Valid Guitar guitar, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "guitars/new";
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
    public String update(@ModelAttribute("guitar") @Valid Guitar guitar, BindingResult bindingResult, @PathVariable("id")int id ){
        if(bindingResult.hasErrors()){
            System.out.println("err");return "guitars/edit";
            }

        guitarDAO.update(id , guitar);
        return "redirect:/guitarsShop";
    }

    //delete request
    @DeleteMapping("/{id}")
    public String deleteGuitar(@PathVariable("id")int id){
        guitarDAO.delete(id);
        return "redirect:/guitarsShop";
    }
}
