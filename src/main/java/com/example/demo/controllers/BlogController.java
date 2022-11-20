package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repo.PostRepository;
import com.example.demo.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController  {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String blogMain(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

   @GetMapping("/blog/add")
    public String blogAdd(Model model)
    {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam(defaultValue = "0")  double title,
                              @RequestParam(defaultValue = "false") boolean anons,
                              @RequestParam(defaultValue = "non")  String full_text,
                              @RequestParam(defaultValue = "10.10.2010")  Date dateAnons,
                              @RequestParam(defaultValue = "0")  int countReaders,
                              Model model)
    {
        Post post = new Post(title, anons, full_text,dateAnons,countReaders);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/blog/filter")
    public String blogFilter(Model model)
    {
        return "blog-filter";
    }

    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam String full_text, Model model)
    {
        List<Post> result = postRepository.searchByRatingStartsWith(full_text);
//        List<Post> result = postRepository.findLikeTitle(title);
        model.addAttribute("result", result);
        return "blog-filter";
    }


}
