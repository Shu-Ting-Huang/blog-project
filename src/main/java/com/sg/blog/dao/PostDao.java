package com.sg.blog.dao;

import java.util.List;

import com.sg.blog.dto.Post;

public interface PostDao {
    void addPost(Post post);
    Post getPostById(int id);
    List<Post> getAllPosts();
    void editPostById(int id, Post post);
    void deletePostById(int id);
}