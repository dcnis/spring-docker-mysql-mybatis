DROP DATABASE IF EXISTS learn_german;

CREATE DATABASE learn_german;

USE learn_german;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE
);



