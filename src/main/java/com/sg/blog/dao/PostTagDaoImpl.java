package com.sg.blog.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sg.blog.dto.Post;
import com.sg.blog.dto.Tag;

public class PostTagDaoImpl implements PostTagDao{
    
    private static DataSource getDataSource() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("blog");
        ds.setUser("root");
        ds.setPassword("123");
        ds.setUseSSL(false);
        ds.setAllowPublicKeyRetrieval(true);
        return ds;
    }

    @Override
    public void addPostTag(Post post, Tag tag) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Post> getPostsByTag(Tag tag) {
        try {
            PostDao postDao = new PostDaoImpl();
            List<Post> result = new ArrayList<>();
            DataSource ds = getDataSource();
            try (Connection conn = ds.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
                while (rs.next()) {
                    if (rs.getInt("tagId") == tag.getId()) {
                        result.add(postDao.getPostById(rs.getInt("postId")));
                    }
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tag> getTagsByPost(Post post) {
        try {
            TagDao tagDao = new TagDaoImpl();
            List<Tag> result = new ArrayList<>();
            DataSource ds = getDataSource();
            try (Connection conn = ds.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tags");
                while (rs.next()) {
                    if (rs.getInt("postId") == post.getId()) {
                        result.add(tagDao.getTagById(rs.getInt("tagId")));
                    }
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removePostTag(Post post, Tag tag) {
        // TODO Auto-generated method stub
        
    }
    
}
