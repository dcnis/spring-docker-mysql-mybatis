package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.service.DialogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class DialogControllerTest {

    @Mock
    private DialogService dialogService;

    @InjectMocks
    private DialogController testee;

    @Test
    public void test(){
        // GIVEN
        Integer lessonid = 5;

        // WHEN
        testee.getDialogOfLesson(lessonid);

        // THEN
        Mockito.verify(dialogService, Mockito.times(1)).getDialogOfLesson(lessonid);

    }

}