package com.sg.blog.controller;

import java.util.Collections;
import java.util.List;

import com.sg.blog.dao.PostDao;
import com.sg.blog.dao.PostDaoImpl;
import com.sg.blog.dao.PostTagDao;
import com.sg.blog.dao.PostTagDaoImpl;
import com.sg.blog.dao.TagDao;
import com.sg.blog.dao.TagDaoImpl;
import com.sg.blog.dto.Post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    
    @GetMapping("home")
    public String home(Model model, @RequestParam(name = "tag", required = false) String tagId) {
        List<Post> postList;
        if (tagId == null) {
            PostDao postDao = new PostDaoImpl();
            postList = postDao.getAllPosts();
        } else {
            PostTagDao postTagDao = new PostTagDaoImpl();
            TagDao tagDao = new TagDaoImpl();
            postList = postTagDao.getPostsByTag(tagDao.getTagById(Integer.parseInt(tagId)));
        }
        Collections.reverse(postList);
        model.addAttribute("posts", postList);
        return "home";
    }

    @GetMapping("post/{postId}")
    public String post(Model model, @PathVariable("postId") String postId) {
        PostDao postDao = new PostDaoImpl();
        model.addAttribute("post", postDao.getPostById(Integer.parseInt(postId)));
        return "post";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }

}
