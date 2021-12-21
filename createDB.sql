DROP DATABASE IF EXISTS blog;
CREATE DATABASE blog;
USE blog;
CREATE TABLE posts(
	postId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content LONGTEXT
);

CREATE TABLE tags(
    tagId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tagName VARCHAR(100) NOT NULL
);

CREATE TABLE post_tags(
    postId INT NOT NULL,
    tagId INT NOT NULL
);

INSERT INTO posts(title, content) VALUES('First Post', 'This is the first post');
INSERT INTO posts(title, content) VALUES('Second Post', 'This is the second post');
INSERT INTO posts(title, content) VALUES('Third Post', 'This is the third post');
INSERT INTO posts(title, content) VALUES('Fourth Post', 'This is the fourth post');

INSERT INTO tags(tagName) VALUES('First Tag');
INSERT INTO tags(tagName) VALUES('Second Tag');

INSERT INTO post_tags(postId, tagId) VALUES(1, 1);
INSERT INTO post_tags(postId, tagId) VALUES(1, 2);
INSERT INTO post_tags(postId, tagId) VALUES(2, 1);
INSERT INTO post_tags(postId, tagId) VALUES(2, 2);
INSERT INTO post_tags(postId, tagId) VALUES(3, 2);
INSERT INTO post_tags(postId, tagId) VALUES(4, 2);