package com.sg.blog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sg.blog.dao.PostDao;
import com.sg.blog.dao.PostDaoImpl;
import com.sg.blog.dao.PostTagDao;
import com.sg.blog.dao.PostTagDaoImpl;
import com.sg.blog.dao.TagDao;
import com.sg.blog.dao.TagDaoImpl;
import com.sg.blog.dto.Post;
import com.sg.blog.dto.Tag;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    PostDao postDao = new PostDaoImpl();
    PostTagDao postTagDao = new PostTagDaoImpl();
    TagDao tagDao = new TagDaoImpl();
    
    @GetMapping("home")
    public String home(Model model, @RequestParam(name = "tag", required = false) String tagId) {
        List<Post> postList;
        if (tagId == null) {
            postList = postDao.getAllPosts();
        } else {
            postList = postTagDao.getPostsByTag(tagDao.getTagById(Integer.parseInt(tagId)));
        }
        Collections.reverse(postList);
        model.addAttribute("posts", postList);
        return "home";
    }

    @GetMapping("post/{postId}")
    public String post(Model model, @PathVariable("postId") String postId) {
        model.addAttribute("post", postDao.getPostById(Integer.parseInt(postId)));
        return "post";
    }

    @GetMapping("admin")
    public String editDelete(Model model) {
        List<Post> postList = postDao.getAllPosts();
        Collections.reverse(postList);
        model.addAttribute("posts", postList);
        return "admin";
    }

    @GetMapping("admin/newpost")
    public String admin() {
        return "newpost";
    }

    @GetMapping("admin/edit")
    public String editPost(Model model, @RequestParam(name="id", required=false) String id) {
        Post post = postDao.getPostById(Integer.parseInt(id));
            model.addAttribute("post", post);
            String tagsString = "";
            for (int i=0; i<post.getTags().size(); i++) {
                tagsString += post.getTags().get(i).getName();
                if ( i != post.getTags().size()-1) {
                    tagsString += ";";
                }
            }
            model.addAttribute("tagsString", tagsString);
            return "edit";
    }

    @PostMapping("admin/delete")
    public String deletePost(@RequestParam("id") String id) {
        postDao.deletePostById(Integer.parseInt(id));
        return "redirect:/home";
    }

    @PostMapping("admin/newpost")
    public String newPost(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("tags") String tagsString) {
        List<String> tags= new ArrayList<String>(Arrays.asList(tagsString.split(";")));
        postDao.addPost(title, content, tags);
        return "redirect:/home";
    }

    @PostMapping("admin/update")
    public String updatePost(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("tags") String tagsString, @RequestParam("id") String id) {
        Post updatedPost = new Post();
        updatedPost.setId(Integer.parseInt(id));
        updatedPost.setTitle(title);
        updatedPost.setContent(content);
        postDao.editPostById(Integer.parseInt(id), updatedPost);

        // update tags information
            // remove old tags
        for (Tag tag: updatedPost.getTags()) {
            postTagDao.removePostTag(updatedPost, tag);
        }
            // add new tags
        List<String> tags= new ArrayList<String>(Arrays.asList(tagsString.split(";")));
        for (String tagName: tags) {
            tagDao.addTagByName(tagName); // create new tag if needed
            postTagDao.addPostTag(updatedPost, tagDao.getTagByName(tagName));
        }
        return "redirect:/home";
    }

}
