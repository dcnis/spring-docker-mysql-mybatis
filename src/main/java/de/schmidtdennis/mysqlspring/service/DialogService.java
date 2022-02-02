package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.DialogMapper;
import de.schmidtdennis.mysqlspring.model.Dialog;
import de.schmidtdennis.mysqlspring.model.request.AddDialogToLessonRequest;
import de.schmidtdennis.mysqlspring.model.response.AddDialogToLessonResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DialogService {

    private final DialogMapper dialogMapper;

    public DialogService(DialogMapper dialogMapper){
        this.dialogMapper = dialogMapper;
    }

    public List<Dialog> getDialogOfLesson(Integer lessonId){
        return dialogMapper.getDialogOfLesson(lessonId);
    }

    public AddDialogToLessonResponse addDialogToLesson(AddDialogToLessonRequest request) {

        int insertedDialogs = 0;

        for(Dialog dialog : request.getDialog()){
            insertedDialogs += dialogMapper.addDialogToLesson(dialog);
        }

        return new AddDialogToLessonResponse(request.getLessonId(), insertedDialogs);
    }
}
