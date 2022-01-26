package de.schmidtdennis.mysqlspring.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddLessonResponse {

    private int insertedRows;
    private Integer lessonId;
}
