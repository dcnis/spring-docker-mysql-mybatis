package de.schmidtdennis.mysqlspring.it;

import de.schmidtdennis.mysqlspring.mapper.DialogMapper;
import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.Dialog;
import de.schmidtdennis.mysqlspring.model.Lesson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:META-INF/application-test.properties")
@SpringJUnitConfig(locations = "classpath:META-INF/test-context.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // The spring application context will be considered "dirty" before each test method, and will be rebuilt. It means that
// your autowired beans will not carry any data between test cases.
public class DialogMapperTest {

    @Autowired
    private DialogMapper testee;

    @Autowired
    private LessonMapper lessonMapper;

    @Test
    public void should_get_Dialog_of_lesson(){
        // GIVEN

        // WHEN
        List<Dialog> dialogOfLesson = testee.getDialogOfLesson(1);

        // THEN
        assertThat(dialogOfLesson.size()).isEqualTo(4);

        assertThat(dialogOfLesson.size()).isEqualTo(4);
        assertThat(dialogOfLesson.get(0).getLessonId()).isEqualTo(1);
        assertThat(dialogOfLesson.get(0).getDialogOrder()).isEqualTo(1);
        assertThat(dialogOfLesson.get(0).getSpeaker()).isEqualTo("安吉");
        assertThat(dialogOfLesson.get(0).getPinyin()).isEqualTo("Nǐ hǎo");
        assertThat(dialogOfLesson.get(0).getChinese()).isEqualTo("你好");
        assertThat(dialogOfLesson.get(0).getEnglish()).isEqualTo("Hello!");

        assertThat(dialogOfLesson.get(1).getLessonId()).isEqualTo(1);
        assertThat(dialogOfLesson.get(1).getDialogOrder()).isEqualTo(2);
        assertThat(dialogOfLesson.get(1).getSpeaker()).isEqualTo("孙硕");
        assertThat(dialogOfLesson.get(1).getPinyin()).isEqualTo("Zhè shì shéi");
        assertThat(dialogOfLesson.get(1).getChinese()).isEqualTo("这是谁");
        assertThat(dialogOfLesson.get(1).getEnglish()).isEqualTo("Who is it?");

        assertThat(dialogOfLesson.get(2).getLessonId()).isEqualTo(1);
        assertThat(dialogOfLesson.get(2).getDialogOrder()).isEqualTo(3);
        assertThat(dialogOfLesson.get(2).getSpeaker()).isEqualTo("安吉");
        assertThat(dialogOfLesson.get(2).getPinyin()).isEqualTo("Nǐ lǎolao láile");
        assertThat(dialogOfLesson.get(2).getChinese()).isEqualTo("你姥姥来了");
        assertThat(dialogOfLesson.get(2).getEnglish()).isEqualTo("Your grandmom is it!");

        assertThat(dialogOfLesson.get(3).getLessonId()).isEqualTo(1);
        assertThat(dialogOfLesson.get(3).getDialogOrder()).isEqualTo(4);
        assertThat(dialogOfLesson.get(3).getSpeaker()).isEqualTo("孙硕");
        assertThat(dialogOfLesson.get(3).getPinyin()).isEqualTo("Tā mā de");
        assertThat(dialogOfLesson.get(3).getChinese()).isEqualTo("他妈的");
        assertThat(dialogOfLesson.get(3).getEnglish()).isEqualTo("Damn!");
    }

    @Test
    public void should_add_single_Dialog_to_lesson(){
        // GIVEN
        Dialog dialog = new Dialog(2, 1, "Dennis", "Rě", "惹", "to provoke");
        Dialog dialog2 = new Dialog(2, 2, "Antiqa", "Zhàngfū", "丈夫", "husband");

        // WHEN
        int insertedRows = testee.addDialogToLesson(dialog);

        // THEN
        assertThat(insertedRows).isEqualTo(1);

        Lesson lesson = lessonMapper.getLesson(2);
        assertThat(lesson).isNotNull();
        assertThat(lesson.getDialog().size()).isEqualTo(1);
        assertThat(lesson.getDialog().get(0).getLessonId()).isEqualTo(2);
        assertThat(lesson.getDialog().get(0).getDialogOrder()).isEqualTo(1);
        assertThat(lesson.getDialog().get(0).getSpeaker()).isEqualTo("Dennis");
        assertThat(lesson.getDialog().get(0).getPinyin()).isEqualTo("Rě");
        assertThat(lesson.getDialog().get(0).getChinese()).isEqualTo("惹");
        assertThat(lesson.getDialog().get(0).getEnglish()).isEqualTo("to provoke");
    }

    @Test
    public void should_add_multiple_Dialogs_to_lesson(){
        // GIVEN
        Dialog dialog = new Dialog(2, 1, "Dennis", "Rě", "惹", "to provoke");
        Dialog dialog2 = new Dialog(2, 2, "Antiqa", "Zhàngfū", "丈夫", "husband");

        // WHEN
        int insertedRows = testee.addDialogToLesson(dialog);
        int insertedRows2 = testee.addDialogToLesson(dialog2);

        // THEN
        assertThat(insertedRows).isEqualTo(1);
        assertThat(insertedRows2).isEqualTo(1);

        Lesson lesson = lessonMapper.getLesson(2);
        assertThat(lesson).isNotNull();
        assertThat(lesson.getDialog().size()).isEqualTo(2);
        assertThat(lesson.getDialog().get(0).getLessonId()).isEqualTo(2);
        assertThat(lesson.getDialog().get(0).getDialogOrder()).isEqualTo(1);
        assertThat(lesson.getDialog().get(0).getSpeaker()).isEqualTo("Dennis");
        assertThat(lesson.getDialog().get(0).getPinyin()).isEqualTo("Rě");
        assertThat(lesson.getDialog().get(0).getChinese()).isEqualTo("惹");
        assertThat(lesson.getDialog().get(0).getEnglish()).isEqualTo("to provoke");

        assertThat(lesson.getDialog().get(1).getLessonId()).isEqualTo(2);
        assertThat(lesson.getDialog().get(1).getDialogOrder()).isEqualTo(2);
        assertThat(lesson.getDialog().get(1).getSpeaker()).isEqualTo("Antiqa");
        assertThat(lesson.getDialog().get(1).getPinyin()).isEqualTo("Zhàngfū");
        assertThat(lesson.getDialog().get(1).getChinese()).isEqualTo("丈夫");
        assertThat(lesson.getDialog().get(1).getEnglish()).isEqualTo("husband");
    }

    @Test
    public void should_return_Dialogs_ordered_by_dialogOrder_when_getDialogsOfLesson_is_called(){
        // GIVEN
        Dialog dialog = new Dialog(2, 2, "Dennis", "Rě", "惹", "to provoke");
        Dialog dialog2 = new Dialog(2, 1, "Antiqa", "Zhàngfū", "丈夫", "husband");
        testee.addDialogToLesson(dialog);
        testee.addDialogToLesson(dialog2);

        // WHEN
        List<Dialog> dialogs = testee.getDialogOfLesson(2);

        // THEN
        assertThat(dialogs.size()).isEqualTo(2);

        assertThat(dialogs.get(0).getLessonId()).isEqualTo(2);
        assertThat(dialogs.get(0).getDialogOrder()).isEqualTo(1);
        assertThat(dialogs.get(0).getSpeaker()).isEqualTo("Antiqa");
        assertThat(dialogs.get(0).getPinyin()).isEqualTo("Zhàngfū");
        assertThat(dialogs.get(0).getChinese()).isEqualTo("丈夫");
        assertThat(dialogs.get(0).getEnglish()).isEqualTo("husband");

        assertThat(dialogs.get(1).getLessonId()).isEqualTo(2);
        assertThat(dialogs.get(1).getDialogOrder()).isEqualTo(2);
        assertThat(dialogs.get(1).getSpeaker()).isEqualTo("Dennis");
        assertThat(dialogs.get(1).getPinyin()).isEqualTo("Rě");
        assertThat(dialogs.get(1).getChinese()).isEqualTo("惹");
        assertThat(dialogs.get(1).getEnglish()).isEqualTo("to provoke");
    }

    @Test
    public void should_return_Dialogs_ordered_by_dialogOrder_when_getLesson_is_called(){
        // GIVEN
        Dialog dialog = new Dialog(2, 2, "Dennis", "Rě", "惹", "to provoke");
        Dialog dialog2 = new Dialog(2, 1, "Antiqa", "Zhàngfū", "丈夫", "husband");
        testee.addDialogToLesson(dialog);
        testee.addDialogToLesson(dialog2);

        // WHEN
        Lesson lesson = lessonMapper.getLesson(2);

        // THEN
        assertThat(lesson).isNotNull();
        assertThat(lesson.getDialog().size()).isEqualTo(2);

        assertThat(lesson.getDialog().get(0).getLessonId()).isEqualTo(2);
        assertThat(lesson.getDialog().get(0).getDialogOrder()).isEqualTo(1);
        assertThat(lesson.getDialog().get(0).getSpeaker()).isEqualTo("Antiqa");
        assertThat(lesson.getDialog().get(0).getPinyin()).isEqualTo("Zhàngfū");
        assertThat(lesson.getDialog().get(0).getChinese()).isEqualTo("丈夫");
        assertThat(lesson.getDialog().get(0).getEnglish()).isEqualTo("husband");

        assertThat(lesson.getDialog().get(1).getLessonId()).isEqualTo(2);
        assertThat(lesson.getDialog().get(1).getDialogOrder()).isEqualTo(2);
        assertThat(lesson.getDialog().get(1).getSpeaker()).isEqualTo("Dennis");
        assertThat(lesson.getDialog().get(1).getPinyin()).isEqualTo("Rě");
        assertThat(lesson.getDialog().get(1).getChinese()).isEqualTo("惹");
        assertThat(lesson.getDialog().get(1).getEnglish()).isEqualTo("to provoke");
    }

    @Test
    public void should_return_Dialogs_ordered_by_dialogOrder_when_getAllLessons_is_called(){
        // GIVEN
        Dialog dialog = new Dialog(2, 2, "Dennis", "Rě", "惹", "to provoke");
        Dialog dialog2 = new Dialog(2, 1, "Antiqa", "Zhàngfū", "丈夫", "husband");
        testee.addDialogToLesson(dialog);
        testee.addDialogToLesson(dialog2);

        // WHEN
        List<Lesson> lessons = lessonMapper.getAllLessons();

        // THEN
        Lesson lesson = lessons.get(1); // Get second lesson

        assertThat(lesson).isNotNull();
        assertThat(lesson.getDialog().size()).isEqualTo(2);

        assertThat(lesson.getDialog().get(0).getLessonId()).isEqualTo(2);
        assertThat(lesson.getDialog().get(0).getDialogOrder()).isEqualTo(1);
        assertThat(lesson.getDialog().get(0).getSpeaker()).isEqualTo("Antiqa");
        assertThat(lesson.getDialog().get(0).getPinyin()).isEqualTo("Zhàngfū");
        assertThat(lesson.getDialog().get(0).getChinese()).isEqualTo("丈夫");
        assertThat(lesson.getDialog().get(0).getEnglish()).isEqualTo("husband");

        assertThat(lesson.getDialog().get(1).getLessonId()).isEqualTo(2);
        assertThat(lesson.getDialog().get(1).getDialogOrder()).isEqualTo(2);
        assertThat(lesson.getDialog().get(1).getSpeaker()).isEqualTo("Dennis");
        assertThat(lesson.getDialog().get(1).getPinyin()).isEqualTo("Rě");
        assertThat(lesson.getDialog().get(1).getChinese()).isEqualTo("惹");
        assertThat(lesson.getDialog().get(1).getEnglish()).isEqualTo("to provoke");
    }



}
