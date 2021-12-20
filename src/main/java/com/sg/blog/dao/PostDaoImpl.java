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
    public void addPost(Post post) {
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        
    }
    
}
