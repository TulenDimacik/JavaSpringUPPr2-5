package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repo.PostRepository;
import com.example.demo.models.Post;
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
    public String blogAdd(@ModelAttribute("posts") Post post)
    {
        return "blog-add";
    }

//    @PostMapping("/blog/add")
//    public String blogPostAdd(@RequestParam(defaultValue = "0")  double title,
//                              @RequestParam(defaultValue = "false") boolean anons,
//                              @RequestParam(defaultValue = "non")  String full_text,
//                              @RequestParam(defaultValue = "10.10.2010")  Date dateAnons,
//                              @RequestParam(defaultValue = "0")  int countReaders,
//                              Model model)
//    {
//        Post post = new Post(title, anons, full_text,dateAnons,countReaders);
//        postRepository.save(post);
//        return "redirect:/";
//    }


    @PostMapping("/blog/add")
    public String blogPostAdd(@ModelAttribute("posts") @Valid Post post, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "blog-add";
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


//    @GetMapping("/blog/{id}")
//    public  String blogDetails(@ModelAttribute("posts") Post post,@PathVariable(value = "id") long id)
//    {
//
//        return "blog-details";
//    }
//
//
//    @GetMapping("/blog/{id}/edit")
//    public  String blogEdit(@ModelAttribute("posts") Post post,@PathVariable(value = "id") long id)
//    {
//        if(!postRepository.existsById(id))
//        {
//            return  "redirect:/blog";
//        }
//        return "blog-edit";
//    }
//
//
//    @PostMapping ("/blog/{id}/edit")
//    public  String blogPostUpdate(@ModelAttribute("posts") @Valid Post post, BindingResult bindingResult, @PathVariable(value = "id") long id)
//    {
//        if(bindingResult.hasErrors())
//            return "blog-edit";
//        postRepository.save(post);
//        return "redirect:/";
//    }
//
//
//    @PostMapping("/blog/{id}/remove")
//    public String blogBlogDelete(@PathVariable(value = "id") long id, Model model){
//        Post post = postRepository.findById(id).orElseThrow();
//        postRepository.delete(post);
//        return "redirect:/";
//    }

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


    /*@GetMapping("/blog/{id}/edit")
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
    }*/
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
//        Post post1 = postRepository.findById(id).orElseThrow();
//        post1.setTitle(post.getTitle());
//        post1.setAnons(post.getAnons());
//        post1.setFull_text(post.getFull_text());
//        post1.setDateAnons(post.getDateAnons());
//        post1.setCountReaders(post.getCountReaders());
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
