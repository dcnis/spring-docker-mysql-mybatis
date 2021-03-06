INSERT INTO Users (first_name, last_name, email) VALUES ("Dennis", "Schmidt", "bgk.dennis@yahoo.de");
INSERT INTO Users (first_name, last_name, email) VALUES ("Antiqa", "Masito", "antiqa.masito@yahoo.de");
INSERT INTO Users (first_name, last_name, email) VALUES ("Andreas", "Ruppel", "andreas.ruppel@yahoo.de");
INSERT INTO Users (first_name, last_name, email) VALUES ("你好", "Älena", "elena.fencel@yahoo.de");

INSERT INTO AddressTypes (name) VALUES ("Invoice address");
INSERT INTO AddressTypes (name) VALUES ("Delivery address");

INSERT INTO Addresses (street, house_number, zip_code, city, country, address_type) VALUES ("Washburn Street", "3142", "05201", "Bennington", "United States", 1);
INSERT INTO Addresses (street, house_number, zip_code, city, country, address_type) VALUES ("Sunset Ct", "38", "29180", "Winnsboro", "United States", 1);
INSERT INTO Addresses (street, house_number, zip_code, city, country, address_type) VALUES ("Wildrose Lane", "132", "48124", "Dearborn", "United States", 1);
INSERT INTO Addresses (street, house_number, zip_code, city, country, address_type) VALUES ("Orphan Road", "2739", "14301", "New York", "United States", 1);

INSERT INTO Difficulties VALUES (1, "Absolute Beginners");
INSERT INTO Difficulties VALUES (2, "Elementary");
INSERT INTO Difficulties VALUES (3, "Intermediate");
INSERT INTO Difficulties VALUES (4, "Advanced");

INSERT INTO Lessons (id, title, discussion, difficulty_id, audio_url, thumbnail_url) VALUES (1, "Let me do it, myself", "One of the things we're proud of at Popup Towers is our hard-fought ability to wring natural dialogues out of less-than-natural voice-actors, a skill that usually involves unleashing Grace at them in varying degrees of rage. And since recording a dialogue this way can take up a bit of time, the result is that we usually end up with a number of variants for each one, usually getting more and more natural as we go along.\n\nIf you're totally new to Chinese we suggest coming back to this show later -- the lesson is a bit tricky for the Absolute Beginner level -- but we wanted to showcase it here for two reasons. The first is that this show features not one but two dialogues. The interesting thing is that the first dialogue sounds a bit stilted while the second sounds extremely natural. And since they basically saying the same thing, we wanted to contrast and compare them, to learn what it is that makes mandarin sound forced and what makes it more colloquial.", 1, "https://dcnispopupchineseaudio.s3.eu-central-1.amazonaws.com/1.mp3", "https://dcnispopupchinesethumbnail.s3.eu-central-1.amazonaws.com/let-it-do-it.jpg");
INSERT INTO Lessons (id, title, discussion, difficulty_id, audio_url, thumbnail_url) VALUES (2, "10 signs you may have an asshole for a husband", "As the whirlwind romance that preceded Mary's honeymoon faded, it became increasing clear that her ill-fated marriage had thrust her into a conjugal death march. Yet despite her husband's passive aggressive hostility, Mary lived in the hope that sheer enthusiasm could somehow break through his frustrated silence, and so continued to ask after him and express an interest in his affairs, and all despite the fact that -- let's face it -- the man was an unremitting asshole.", 1, "https://dcnispopupchineseaudio.s3.eu-central-1.amazonaws.com/2.mp3", "https://dcnispopupchinesethumbnail.s3.eu-central-1.amazonaws.com/10signsyourhusbandisanashole.jpg");
INSERT INTO Lessons (id, title, discussion, difficulty_id, audio_url, thumbnail_url) VALUES (3, "The Public Security Alarm", "Through many countries and over many seas I have come, dear brother, to this futuristic society in which you live, only to find myself astonished and humbled by its paradoxical embrace of social monitoring tools which seem to assure social order, yet also remain respectful of individual privacy and democratic rights. But alas! What is that pale cry? From whence comes that demonic shriek which fills the air with groans of woe, and strikes my soul with fear?\n\nLearning Chinese? This is a fairly easy beginning lesson that covers the basics of asking and answering questions, such as asking what things are. We also give you a great phrase you can use to make yourself seem more understood, and help you apologize as you push yourself off the subway. So if you're just getting started with mandarin, check it out!", 1, "https://dcnispopupchineseaudio.s3.eu-central-1.amazonaws.com/3.mp3", "https://dcnispopupchinesethumbnail.s3.eu-central-1.amazonaws.com/10signsyourhusbandisanashole.jpg");
INSERT INTO Lessons (id, title, discussion, difficulty_id) VALUES (4, "Shattered Dreams", "Rather than a regular podcast, today we are pleased to publish a longer Elementary dialogue designed to test your listening comprehension. The language used here is not terribly difficult, but it is spoken at native pace and with the sort of emotional inflection you'll find living and working in China. So take a listen and click through to our quiz to see how much you understand. Our annotated transcript is there as always in case you have difficulty.", 2);
INSERT INTO Lessons (id, title, discussion, difficulty_id) VALUES (5, "Travelling Light", "Xiao Wang strained to tighten the security strap around her luggage, pressing her knee against the bulging fabric to muscle the suitcase closed. She was not sure when or how packing had become this problematic. For while she had long ago become accustomed to the need for travelling light, it nonetheless seemed that there was somehow more and more to pack for each trip.", 2);

INSERT INTO UserLessons (user_id, lesson_id, last_seen) VALUES (1, 4, "2020-01-01 10:10:10");
INSERT INTO UserLessons (user_id, lesson_id, last_seen) VALUES (1, 3, "2019-01-01 12:41:10");
INSERT INTO UserLessons (user_id, lesson_id, last_seen) VALUES (1, 2, "2022-01-01 09:01:10");
INSERT INTO UserLessons (user_id, lesson_id, last_seen) VALUES (2, 1, "2021-04-11 10:10:10");
INSERT INTO UserLessons (user_id, lesson_id, last_seen ) VALUES (3, 1, "2018-01-01 12:01:10");

INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (1, "èrshíyī", "二十一", "21", 1);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (1, "kuài", "块", "(n.) piece", 2);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (1, "qián", "钱", "(n.) money", 3);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (1, "Wǒ", "我", "I", 4);

INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (3, "Tàitǎnníkè hào", "泰坦尼克号", "(n.) Titanic", 1);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (3, "zánmen", "咱们", "we", 2);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (3, "hǎishuǐ", "海水", "(n.) seawater", 3);
INSERT INTO Vocabularies (lesson_id, pinyin, chinese, english, vocabulary_order) VALUES (3, "bīngchuān", "冰川", "(n.) glacier (colloquially iceberg as well", 4);

INSERT INTO Dialogs (lesson_id, dialog_order, speaker, pinyin, chinese, english) VALUES (1, 1, "安吉", "Nǐ hǎo", "你好", "Hello!");
INSERT INTO Dialogs (lesson_id, dialog_order, speaker, pinyin, chinese, english) VALUES (1, 2, "孙硕", "Zhè shì shéi", "这是谁", "Who is it?");
INSERT INTO Dialogs (lesson_id, dialog_order, speaker, pinyin, chinese, english) VALUES (1, 3, "安吉", "Nǐ lǎolao láile", "你姥姥来了", "Your grandmom is it!");
INSERT INTO Dialogs (lesson_id, dialog_order, speaker, pinyin, chinese, english) VALUES (1, 4, "孙硕", "Tā mā de", "他妈的", "Damn!");
