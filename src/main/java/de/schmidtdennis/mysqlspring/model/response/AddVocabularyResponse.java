package de.schmidtdennis.mysqlspring.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddVocabularyResponse {

    private Integer lessonId;
    private Integer insertedVocabularies;

}
