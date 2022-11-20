package com.example.demo.controllers;

import com.example.demo.models.Human;
import com.example.demo.repo.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class HumanController {
    @Autowired
    private HumanRepository humanRepository;

    @GetMapping("/human")
    public String blogMain(Model model)
    {
        Iterable<Human> humans = humanRepository.findAll();
        model.addAttribute("humans", humans);
        return "human-main";
    }



    @PostMapping("/human/add")
    public String blogPostAdd(@RequestParam(defaultValue = "")  String lastName,
                              @RequestParam(defaultValue = "0") float height,
                              @RequestParam(defaultValue = "false")  boolean gender,
                              @RequestParam(defaultValue = "10.10.2010") Date birthday,
                              @RequestParam(defaultValue = "0")  double weight,
                              Model model)
    {
        Human human = new Human(lastName, height, gender,birthday,weight);
        humanRepository.save(human);
        return "redirect:/human";
    }

    @PostMapping("/human/filter")
    public String blogResult(@RequestParam(defaultValue = "") String lastName, Model model)
    {
       //List<Human> result = humanRepository.findByLastNameContains(lastName);
        List<Human> result = humanRepository.findByLastNameEquals(lastName);
        model.addAttribute("result", result);
        return "human-main";
    }
}
