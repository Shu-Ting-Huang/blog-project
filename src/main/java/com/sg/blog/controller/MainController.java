package com.sg.blog.controller;

import java.util.List;

import com.sg.blog.dao.PostDao;
import com.sg.blog.dao.PostDaoImpl;
import com.sg.blog.dto.Post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    @GetMapping("home")
    public String home(Model model) {
        PostDao dao = new PostDaoImpl();
		List<Post> x = dao.getAllPosts();
        model.addAttribute("posts", x);
        return "home";
    }
}
