package com.sg.blog.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sg.blog.dto.Tag;

public class TagDaoImpl implements TagDao {

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
    public void addTag(Tag tag) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Tag getTagById(int id) {
        try {
            DataSource ds = getDataSource();
            try ( Connection conn = ds.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tags");
                while (rs.next()) {
                    if (rs.getInt("tagId") == id) {
                        Tag result = new Tag();
                        result.setId(id);
                        result.setName(rs.getString("tagName"));
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
    public List<Tag> getAllTags() {
        try {
            List<Tag> result = new ArrayList<>();
            DataSource ds = getDataSource();
            try (Connection conn = ds.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tags");
                while (rs.next()) {
                    Tag tempTag = new Tag();
                    tempTag.setId(rs.getInt("tagId"));
                    tempTag.setName("tagName");
                    result.add(tempTag);
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void editTagById(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteTagById(int id) {
        // TODO Auto-generated method stub
        
    }
    
}
