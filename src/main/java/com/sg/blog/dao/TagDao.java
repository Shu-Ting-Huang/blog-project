package com.sg.blog.dao;

import java.util.List;

import com.sg.blog.dto.Tag;

public interface TagDao {
    void addTag(Tag tag);
    Tag getTagById(int id);
    List<Tag> getAllTags();
    void editTagById(int id);
    void deleteTagById(int id);
}