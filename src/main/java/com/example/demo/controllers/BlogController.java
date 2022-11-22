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
import java.util.Optional;

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


    @GetMapping("/blog/{id}")
    public  String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        if(!postRepository.existsById(id))
        {
            return  "redirect:/blog";
        }
        return "blog-details";
    }


    @GetMapping("/blog/{id}/edit")
    public  String blogEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!postRepository.existsById(id))
        {
            return  "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog-edit";
    }


    @PostMapping ("/blog/{id}/edit")
    public  String blogPostUpdate(@PathVariable(value = "id") long id,
                                  @RequestParam(defaultValue = "0")  double title,
                                  @RequestParam(defaultValue = "false") boolean anons,
                                  @RequestParam(defaultValue = "non")  String full_text,
                                  @RequestParam(defaultValue = "10.10.2010")  Date dateAnons,
                                  @RequestParam(defaultValue = "0")  int countReaders,
                                  Model model)
    {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        post.setDateAnons(dateAnons);
        post.setCountReaders(countReaders);
        postRepository.save(post);
        return "redirect:/";
    }


    @PostMapping("/blog/{id}/remove")
    public String blogBlogDelete(@PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/";
    }


}
