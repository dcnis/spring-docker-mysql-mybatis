SET MODE MYSQL;

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

CREATE TABLE Difficulties (
    id INTEGER PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE Lessons (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    discussion TEXT,
    difficulty_id INTEGER NOT NULL,
    thumbnail_url VARCHAR(255),
    audio_url VARCHAR(255),
    FOREIGN KEY (difficulty_id) REFERENCES Difficulties(id)
);

CREATE TABLE UserLessons (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INTEGER NOT NULL,
    lesson_id INTEGER NOT NULL,
    liked BOOLEAN DEFAULT FALSE,
    last_seen DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (lesson_id) REFERENCES Lessons(id)
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

CREATE TABLE Vocabularies (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    lesson_id INTEGER NOT NULL,
    pinyin VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_sv_0900_ai_ci NOT NULL,
    chinese VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_sv_0900_ai_ci NOT NULL,
    english VARCHAR(255) NOT NULL,
    sentence VARCHAR(255),
    vocabulary_order INTEGER NOT NULL,
    FOREIGN KEY (lesson_id) REFERENCES Lessons(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_sv_0900_ai_ci;


