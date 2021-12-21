package com.sg.blog.dto;

import java.util.List;

import com.sg.blog.dao.PostTagDao;
import com.sg.blog.dao.PostTagDaoImpl;

public class Post {
    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTags() {
        PostTagDao postTagDao = new PostTagDaoImpl();
        return postTagDao.getTagsByPost(this);
    }
}