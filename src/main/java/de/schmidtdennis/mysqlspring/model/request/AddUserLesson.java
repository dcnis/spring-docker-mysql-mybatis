package de.schmidtdennis.mysqlspring.model.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddUserLesson {

    @NotNull(message = "userId must not be null")
    private Integer userId;

    @NotNull(message = "lessonId must not be null")
    private Integer lessonId;
}
