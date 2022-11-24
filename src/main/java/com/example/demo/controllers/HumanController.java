package com.example.demo.controllers;

import com.example.demo.models.Human;
import com.example.demo.models.Post;
import com.example.demo.repo.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HumanController {
    @Autowired
    private HumanRepository humanRepository;

    @GetMapping("/human")
    public String humanMain(Model model)
    {
        Iterable<Human> humans = humanRepository.findAll();
        model.addAttribute("humans", humans);
        return "human-main";
    }


    @GetMapping("/human/add")
    public String humanAdd(@ModelAttribute("humans") Human human)
    {
        return "human-add";
    }

    @PostMapping("/human/add")
    public String humanPostAdd(@ModelAttribute("humans") @Valid Human human, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "human-add";
        humanRepository.save(human);
        return "redirect:/human";
    }

    @PostMapping("/human/filter")
    public String humanResult(@RequestParam(defaultValue = "") String lastName, Model model)
    {
        List<Human> result = humanRepository.findByLastNameContains(lastName);
        //List<Human> result = humanRepository.findByWeightContains(weight);
        model.addAttribute("result", result);
        return "human-main";
    }


    @GetMapping("/human/{id}/edit")
    public  String humanDetails(@PathVariable(value = "id") long id, Model model)
    {

        Human res1 = humanRepository.findById(id).orElseThrow();
        model.addAttribute("human",res1);
        return "human-edit";
    }


    @PostMapping ("/human/{id}/edit")
    public  String humanUpdate(@ModelAttribute("human") @Valid Human human,
                               BindingResult bindingResult,
                               @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "human-edit";
        humanRepository.save(human);
        return "redirect:/human";
    }


    @GetMapping("/human/{id}/remove")
    public  String humanDelete(@PathVariable(value = "id") long id, Model model)
    {
        Human human = humanRepository.findById(id).orElseThrow();
        humanRepository.delete(human);
        return "redirect:/human";
    }
}