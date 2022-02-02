package de.schmidtdennis.mysqlspring.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Dialog implements Serializable {

    private Integer id;
    private Integer lessonId;

    @NotNull
    private Integer dialogOrder;

    @NotNull
    private String speaker;

    @NotNull
    private String pinyin;

    @NotNull
    private String chinese;

    @NotNull
    private String english;

    public Dialog(Integer lessonId, Integer dialogOrder, String speaker, String pinyin, String chinese, String english){
        this.lessonId = lessonId;
        this.dialogOrder = dialogOrder;
        this.speaker = speaker;
        this.pinyin = pinyin;
        this.chinese = chinese;
        this.english = english;
    }

}
