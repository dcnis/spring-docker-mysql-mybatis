package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class Vocabulary implements Serializable {

    private Integer id;

    @NotNull
    private Integer lessonId;

    @NotNull
    private String pinyin;

    @NotNull
    private String chinese;

    @NotNull
    private String english;

    private Integer vocabularyOrder;

}
