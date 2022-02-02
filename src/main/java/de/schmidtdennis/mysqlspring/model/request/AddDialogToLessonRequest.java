package de.schmidtdennis.mysqlspring.model.request;

import de.schmidtdennis.mysqlspring.model.Dialog;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class AddDialogToLessonRequest {

    @NotNull
    private Integer lessonId;

    @NotNull
    private List<Dialog> dialog;

}
