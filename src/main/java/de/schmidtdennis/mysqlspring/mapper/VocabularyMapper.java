package de.schmidtdennis.mysqlspring.mapper;

import de.schmidtdennis.mysqlspring.model.Vocabulary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VocabularyMapper {

    List<Vocabulary> getVocabularyOfLesson(Integer lessonId);

    int addVocabulary(Integer lessonId, Vocabulary vocabulary);

}
