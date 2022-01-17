package de.schmidtdennis.mysqlspring.model.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserLesson {

    @NotNull(message = "userId must not be null")
    private Integer userId;

    @NotNull(message = "lessonId must not be null")
    private Integer lessonId;

}
