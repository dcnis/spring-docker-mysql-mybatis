SET MODE MYSQL;

INSERT INTO Difficulties (id, description) VALUES (1, 'Absolute Beginners');
INSERT INTO Difficulties (id, description) VALUES (2, 'Elementary');
INSERT INTO Difficulties (id, description) VALUES (3, 'Intermediate');
INSERT INTO Difficulties (id, description) VALUES (4, 'Advanced');

INSERT INTO Lessons (title, discussion, difficulty_id, thumbnail_url, audio_url) VALUES ('Let me do it, myself', 'Discussion for Let me do it, myself', 1, 'thumbnailurl', 'audiourl');
INSERT INTO Lessons (title, discussion, difficulty_id) VALUES ('10 signs you may have an asshole for a husband', 'Discussion for 10 signs you may have an asshole for a husband', 1);
INSERT INTO Lessons (title, discussion, difficulty_id) VALUES ('The Public Security Alarm', 'Discussion for The Public Security Alarm', 1);
INSERT INTO Lessons (title, discussion, difficulty_id) VALUES ('Shattered Dreams', 'Discussion for Shattered Dreams', 2);
INSERT INTO Lessons (title, discussion, difficulty_id) VALUES ('Travelling Light', 'Discussion for Travelling Light', 2);
