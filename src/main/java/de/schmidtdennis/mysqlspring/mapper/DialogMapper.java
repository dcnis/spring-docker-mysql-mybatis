package de.schmidtdennis.mysqlspring.mapper;

import de.schmidtdennis.mysqlspring.model.Dialog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DialogMapper {

    List<Dialog> getDialogOfLesson(Integer lessonId);

    int addDialogToLesson(Dialog request);

}
