package com.sg.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sg.blog.dto.Post;

import org.springframework.stereotype.Repository;

@Repository
public class PostDaoImpl implements PostDao {

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
    public void addPost(String title, String content, List<String> tagNames) {
        try {
            DataSource ds = getDataSource();
            try (Connection conn = ds.getConnection()) {
                PreparedStatement pStmt = conn.prepareCall("INSERT INTO posts (title, content) VALUES(?,?)");
                pStmt.setString(1, title);
                pStmt.setString(2, content);
                pStmt.executeUpdate();
            }
            try (Connection conn = ds.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
                int postId = -1000;
                while (rs.next()) {
                    postId = rs.getInt("postId");
                }
                TagDao tagDao = new TagDaoImpl();
                PostTagDao postTagDao = new PostTagDaoImpl();
                for (String tagName: tagNames) {
                    if (tagDao.getTagByName(tagName) == null) {
                        tagDao.addTagByName(tagName);
                    }
                    postTagDao.addPostTag(getPostById(postId), tagDao.getTagByName(tagName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Post getPostById(int id) {
        try {
            DataSource ds = getDataSource();
            try ( Connection conn = ds.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
                while (rs.next()) {
                    if (rs.getInt("postId") == id) {
                        Post result = new Post();
                        result.setId(id);
                        result.setTitle(rs.getString("title"));
                        result.setContent(rs.getString("content"));
                        return result;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        try {
            List<Post> result = new ArrayList<>();
            DataSource ds = getDataSource();
            try (Connection conn = ds.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
                while (rs.next()) {
                    Post tempPost = new Post();
                    tempPost.setId(rs.getInt("postId"));
                    tempPost.setTitle(rs.getString("title"));
                    tempPost.setContent(rs.getString("content"));
                    result.add(tempPost);
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void editPostById(int id, Post post) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deletePostById(int id) {
        try {
            DataSource ds = getDataSource();
            try (Connection conn = ds.getConnection()) {
                PreparedStatement pStmt = conn.prepareCall("DELETE FROM posts WHERE postId = " + id);
                pStmt.executeUpdate();
                pStmt = conn.prepareCall("DELETE FROM post_tags WHERE postId = " + id);
                pStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
