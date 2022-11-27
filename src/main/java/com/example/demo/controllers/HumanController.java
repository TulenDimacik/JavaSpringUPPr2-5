package com.example.demo.controllers;

import com.example.demo.models.Human;
import com.example.demo.models.Pasport;
import com.example.demo.models.Post;
import com.example.demo.models.University;
import com.example.demo.repo.HumanRepository;
import com.example.demo.repo.PasportRepository;
import com.example.demo.repo.UniversityRepository;
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
    private PasportRepository pasportRepository;
    @Autowired
    private HumanRepository humanRepository;
    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/human")
    public String humanMain(Model model)
    {
        Iterable<Human> humans = humanRepository.findAll();
        model.addAttribute("humans", humans);
        Iterable<University> universities = universityRepository.findAll();
        model.addAttribute("universities", universities);
        return "human-main";
    }


    @GetMapping("/human/add")
    public String humanAdd(@ModelAttribute("humans") Human human, Model pasprt)
    {
        Iterable<Pasport> pasport = pasportRepository.findAll();
        pasprt.addAttribute("pasport", pasport);
        return "human-add";
    }

    @PostMapping("/human/add")
    public String humanPostAdd(@ModelAttribute("humans") @Valid Human human, BindingResult bindingResult,
                               @RequestParam String number, Model pasprt)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Pasport> pasport = pasportRepository.findAll();
            pasprt.addAttribute("pasport", pasport);
            return "human-add";
        }
        human.setPasport(pasportRepository.findByNumber(number));
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


    @GetMapping("/human/{id_human}/{id_univ}/remove")
    public  String humanDelete(@PathVariable(value = "id_human") long id_human,@PathVariable(value = "id_univ") Long id_univ, Model model)
    {
        Human human = humanRepository.findById(id_human).orElseThrow();
        University university2 = universityRepository.findById(id_univ).orElseThrow();
        human.getUniversities().remove(university2);
        humanRepository.save(human);
        return "redirect:/human";
    }


    @GetMapping("/human/university/add")
    private String Main(Model model){
        Iterable<Human> human = humanRepository.findAll();
        model.addAttribute("humans", human);
        Iterable<University> universities = universityRepository.findAll();
        model.addAttribute("universities", universities);
        return "human-university-add";
    }

    @PostMapping("/human/university/add")
    public String blogPostAdd(@RequestParam Long human, @RequestParam Long universiti, Model model)
    {
        Human human2 = humanRepository.findById(human).orElseThrow();
        University university2 = universityRepository.findById(universiti).orElseThrow();
        human2.getUniversities().add(university2);
        //university2.getStudents().add(student2);
        humanRepository.save(human2);
        return "redirect:/human";
    }

}