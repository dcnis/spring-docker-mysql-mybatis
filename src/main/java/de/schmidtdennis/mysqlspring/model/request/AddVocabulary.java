package de.schmidtdennis.mysqlspring.model.request;

import de.schmidtdennis.mysqlspring.model.Vocabulary;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class AddVocabulary {

    @NotNull
    private Integer lessonId;

    @Valid
    private List<Vocabulary> vocabulary;

}
