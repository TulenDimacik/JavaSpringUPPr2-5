package com.example.demo.controllers;

import com.example.demo.models.Address;
import com.example.demo.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repo.PostRepository;
import com.example.demo.models.Post;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@PreAuthorize("hasAnyAuthority('ADMIN','USER','HUMANOID')")
public class BlogController  {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    public AddressRepository addressRepository;

    @GetMapping("/")
    public String blogMain(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

   @GetMapping("/blog/add")
    public String blogAdd(@ModelAttribute("posts") Post post, Model addr)
    {
        Iterable<Address> address = addressRepository.findAll();
        addr.addAttribute("address",address);
        return "blog-add";
    }


    @PostMapping("/blog/add")
    public String blogPostAdd(@ModelAttribute("posts") @Valid Post post, BindingResult bindingResult, @RequestParam String street, Model addr)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Address> address = addressRepository.findAll();
            addr.addAttribute("address",address);
            return "blog-add";
        }
        post.setAddress(addressRepository.findByStreet(street));
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
        model.addAttribute("result", result);
        return "blog-filter";
    }

    @GetMapping("/blog/{id}")
    public  String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("posts",res);
        if(!postRepository.existsById(id))
        {
            return  "redirect:/blog";
        }
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public  String blogEdit(@PathVariable(value = "id") long id, Model model)
    {
        Post res = postRepository.findById(id).orElseThrow();
        model.addAttribute("post",res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public  String blogPostUpdate(@ModelAttribute("post") @Valid Post post,
                                  BindingResult bindingResult,
                                  @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "blog-edit";
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
