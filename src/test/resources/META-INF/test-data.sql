SET MODE MYSQL;

INSERT INTO Users (id, first_name, last_name, email) VALUES (1, 'John', 'Doe', 'john.doe@mail.de');
INSERT INTO Users (id, first_name, last_name, email) VALUES (2, 'Jane', 'Smith', 'jane.smith@mail.de');

INSERT INTO Difficulties (id, description) VALUES (1, 'Absolute Beginners');
INSERT INTO Difficulties (id, description) VALUES (2, 'Elementary');
INSERT INTO Difficulties (id, description) VALUES (3, 'Intermediate');
INSERT INTO Difficulties (id, description) VALUES (4, 'Advanced');

INSERT INTO Lessons (id, title, discussion, difficulty_id, thumbnail_url, audio_url) VALUES (1, 'Let me do it, myself', 'Discussion for Let me do it, myself', 1, 'thumbnailurl', 'audiourl');
INSERT INTO Lessons (id, title, discussion, difficulty_id) VALUES (2, '10 signs you may have an asshole for a husband', 'Discussion for 10 signs you may have an asshole for a husband', 1);
INSERT INTO Lessons (id, title, discussion, difficulty_id) VALUES (3, 'The Public Security Alarm', 'Discussion for The Public Security Alarm', 1);
INSERT INTO Lessons (id, title, discussion, difficulty_id) VALUES (4, 'Shattered Dreams', 'Discussion for Shattered Dreams', 2);
INSERT INTO Lessons (id, title, discussion, difficulty_id) VALUES (5, 'Travelling Light', 'Discussion for Travelling Light', 2);
