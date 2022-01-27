package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.service.AudioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class AudioControllerTest {

    @Mock
    private AudioService audioService;

    @InjectMocks
    private AudioController testee;

    @Test
    public void should_call_audioService_getAudio() throws IOException {
        // GIVEN
        Integer lessonId = 1;

        // WHEN
        testee.getAudio(lessonId);

        // THEN
        Mockito.verify(audioService, Mockito.times(1)).getAudio(lessonId);
    }


}