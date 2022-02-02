package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.Dialog;
import de.schmidtdennis.mysqlspring.model.request.AddDialogToLessonRequest;
import de.schmidtdennis.mysqlspring.model.response.AddDialogToLessonResponse;
import de.schmidtdennis.mysqlspring.service.DialogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DialogController {

    private final DialogService dialogService;

    public DialogController(DialogService dialogService){
        this.dialogService = dialogService;
    }

    @GetMapping("dialog/lessonId/{lessonId}")
    public List<Dialog> getDialogOfLesson(@PathVariable("lessonId") Integer lessonId){
        return dialogService.getDialogOfLesson(lessonId);
    }

    @PostMapping("dialog")
    public AddDialogToLessonResponse addDialogToLesson(@RequestBody @Valid AddDialogToLessonRequest request){
        return dialogService.addDialogToLesson(request);
    }

}
