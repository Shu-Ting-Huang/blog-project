package com.sg.blog.controller;

import com.sg.blog.dao.PostDao;
import com.sg.blog.dao.PostDaoImpl;
import com.sg.blog.dao.PostTagDao;
import com.sg.blog.dao.PostTagDaoImpl;
import com.sg.blog.dao.TagDao;
import com.sg.blog.dao.TagDaoImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    
    @GetMapping("home")
    public String home(Model model) {
        PostDao dao = new PostDaoImpl();
        model.addAttribute("posts", dao.getAllPosts());
        return "home";
    }

    @GetMapping("tag/{tagId}")
    public String tag(Model model, @PathVariable("tagId") String tagId) {
        PostTagDao postTagDao = new PostTagDaoImpl();
        TagDao tagDao = new TagDaoImpl();
        model.addAttribute("posts", postTagDao.getPostsByTag(tagDao.getTagById(Integer.parseInt(tagId))));
        return "home";
    }
}
