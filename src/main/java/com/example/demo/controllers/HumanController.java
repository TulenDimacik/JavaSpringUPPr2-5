package com.example.demo.controllers;

import com.example.demo.models.Human;
import com.example.demo.models.Post;
import com.example.demo.repo.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
       List<Human> result = humanRepository.findByLastNameContains(lastName);
        //List<Human> result = humanRepository.findByWeightContains(weight);
        model.addAttribute("result", result);
        return "human-main";
    }


    @GetMapping("/human/{id}/edit")
    public  String HumanDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Human> post = humanRepository.findById(id);
        ArrayList<Human> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("human",res);
        if(!humanRepository.existsById(id))
        {
            return  "redirect:/human";
        }
        return "human-main";
    }


    @PostMapping ("/human/{id}/edit")
    public  String HumanPostUpdate(@PathVariable(value = "id") long id,
                                  @RequestParam(defaultValue = "")  String lastName,
                                  @RequestParam(defaultValue = "0") float height,
                                  @RequestParam(defaultValue = "false")  boolean gender,
                                  @RequestParam(defaultValue = "10.10.2010") Date birthday,
                                  @RequestParam(defaultValue = "0")  double weight,
                                  Model model)
    {
        Human human = humanRepository.findById(id).orElseThrow();
        human.setLastName(lastName);
        human.setHeight(height);
        human.setGender(gender);
        human.setBirthday(birthday);
        human.setWeight(weight);
        humanRepository.save(human);
        return "redirect:/human";
    }


    @GetMapping("/human/{id}/remove")
    public  String HumanDelDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Human> post = humanRepository.findById(id);
        ArrayList<Human> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("human",res);
        if(!humanRepository.existsById(id))
        {
            return  "redirect:/human";
        }
        return HumanDelete(id,model);
    }
    @PostMapping("/human/{id}/remove")
    public String HumanDelete(@PathVariable(value = "id") long id, Model model){
        Human human = humanRepository.findById(id).orElseThrow();
        humanRepository.delete(human);
        return "redirect:/human";
    }
}
