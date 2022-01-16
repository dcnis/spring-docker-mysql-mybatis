package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.request.AddUserLesson;
import de.schmidtdennis.mysqlspring.model.response.UserLessonResult;
import de.schmidtdennis.mysqlspring.service.UserLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserLessonController {

    private final UserLessonService userLessonService;

    @Autowired
    public UserLessonController(UserLessonService userLessonService) {
        this.userLessonService = userLessonService;
    }

    @GetMapping("userlessons")
    public UserLessonResult getUserLessons(@RequestParam(value = "userId", required = false) Integer userId,
                                           @RequestParam(value = "email", required = false) String email) {

        if (userId == null && email == null) throw new IllegalArgumentException("userId und email fehlen");

        return userLessonService.getUserLessons(userId, email);
    }

    @PostMapping("userlessons/add")
    public int addUserLesson(@RequestBody @Valid AddUserLesson request){
        return userLessonService.addUserLesson(request);
    }
}
