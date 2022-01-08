DROP DATABASE IF EXISTS learn_german;

CREATE DATABASE learn_german;

USE learn_german;

CREATE TABLE Users (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE AddressTypes (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
);

CREATE TABLE Addresses (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    house_number VARCHAR(25) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    address_type INTEGER NOT NULL,
    FOREIGN KEY (address_type) REFERENCES AddressTypes(id)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE Episodes (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    url VARCHAR(255)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE LastWatched (
    user_id INTEGER,
    episode_id INTEGER,
    last_viewed TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id,episode_id),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (episode_id) REFERENCES Episodes(id)
);


