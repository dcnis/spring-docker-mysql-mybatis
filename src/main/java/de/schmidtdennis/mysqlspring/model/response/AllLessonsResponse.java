package de.schmidtdennis.mysqlspring.model.response;

import de.schmidtdennis.mysqlspring.model.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AllLessonsResponse {

    private int count;
    private List<Lesson> data;

}
