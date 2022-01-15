package de.schmidtdennis.mysqlspring.model.response;

import de.schmidtdennis.mysqlspring.model.UserLesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLessonResult {

    private int count;
    private List<UserLesson> data;

}
