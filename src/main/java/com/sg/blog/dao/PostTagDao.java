package com.sg.blog.dao;

import java.util.List;

import com.sg.blog.dto.Post;
import com.sg.blog.dto.Tag;

public interface PostTagDao {
    void addPostTag(Post post, Tag tag);
    List<Post> getPostsByTag(Tag tag);
    List<Tag> getTagsByPost(Post post);
    void removePostTag(Post post, Tag tag);
}