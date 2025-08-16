-- Drop existing tables if they exist (to avoid conflicts)
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS users;

-- Create Author Table
CREATE TABLE author
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    bio        VARCHAR(500) NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- Create Category Table
CREATE TABLE category
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- Create Users Table
CREATE TABLE users
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    phone      VARCHAR(255),
    role       ENUM('ADMIN','USER'),
    created_at DATETIME(6),
    updated_at DATETIME(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT UK_users_email UNIQUE (email)
) ENGINE=InnoDB;

-- Create Book Table
CREATE TABLE book
(
    id                      BIGINT        NOT NULL AUTO_INCREMENT,
    title                   VARCHAR(255)  NOT NULL,
    description             VARCHAR(1000) NOT NULL,
    publisher               VARCHAR(255)  NOT NULL,
    publication_date        DATE,
    author_id               BIGINT        NOT NULL,
    category_id             BIGINT        NOT NULL,
    book_file_url           VARCHAR(255),
    book_file_url_public_id VARCHAR(255),
    cover_image_url         VARCHAR(255),
    cover_image_public_id   VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_book_author FOREIGN KEY (author_id) REFERENCES author (id),
    CONSTRAINT FK_book_category FOREIGN KEY (category_id) REFERENCES category (id)
) ENGINE=InnoDB;
