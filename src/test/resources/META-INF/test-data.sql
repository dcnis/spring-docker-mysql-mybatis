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

INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (1, 'èrshíyī', '二十一', '21', 1);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (1, 'kuài', '块', '(n.) piece', 2);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (1, 'qián', '钱', '(n.) money', 3);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (1, 'Wǒ', '我', 'I', 4);

INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (3, 'Tàitǎnníkè hào', '泰坦尼克号', '(n.) Titanic', 1);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (3, 'zánmen', '咱们', 'we', 2);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (3, 'hǎishuǐ', '海水', '(n.) seawater', 3);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (3, 'bīngchuān', '冰川', '(n.) glacier (colloquially iceberg as well', 4);

INSERT INTO Dialogs (lesson_id, dialog_order, speaker, pinyin, chinese, english) VALUES (1, 1, '安吉', 'Nǐ hǎo', '你好', 'Hello!');
INSERT INTO Dialogs (lesson_id, dialog_order, speaker, pinyin, chinese, english) VALUES (1, 2, '孙硕', 'Zhè shì shéi', '这是谁', 'Who is it?');
INSERT INTO Dialogs (lesson_id, dialog_order, speaker, pinyin, chinese, english) VALUES (1, 3, '安吉', 'Nǐ lǎolao láile', '你姥姥来了', 'Your grandmom is it!');
INSERT INTO Dialogs (lesson_id, dialog_order, speaker, pinyin, chinese, english) VALUES (1, 4, '孙硕', 'Tā mā de', '他妈的', 'Damn!');
