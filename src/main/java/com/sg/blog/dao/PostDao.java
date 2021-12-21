package com.sg.blog.dao;

import java.util.List;

import com.sg.blog.dto.Post;

public interface PostDao {
    void addPost(String title, String content, List<String> tagNames);
    Post getPostById(int id);
    List<Post> getAllPosts();
    void editPostById(int id, Post post);
    void deletePostById(int id);
}