package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.AudioFile;
import de.schmidtdennis.mysqlspring.service.AudioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

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
        ResponseEntity<InputStreamResource> audio = testee.getAudio(lessonId);

        // THEN
        Mockito.verify(audioService, Mockito.times(1)).getAudio(lessonId);
        assertThat(audio).isNull();
    }

    @Test
    public void should_return_audio_as_InputStreamResource() throws IOException {
        // GIVEN
        Integer lessonId = 100;
        byte[] data = new byte[2];
        data[0] = 20;
        data[1] = 10;
        AudioFile mockAudio = new AudioFile();
        mockAudio.setData(data);
        mockAudio.setFilename("filename.mp3");
        mockAudio.setLessonId(lessonId);

        Mockito.when(audioService.getAudio(lessonId)).thenReturn(mockAudio);

        // WHEN
        ResponseEntity<InputStreamResource> audio = testee.getAudio(lessonId);

        // THEN
        Mockito.verify(audioService, Mockito.times(1)).getAudio(lessonId);
        assertThat(audio.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(audio.getBody()).isNotNull();
        assertThat(audio.getBody()).isExactlyInstanceOf(InputStreamResource.class);

        InputStream is = audio.getBody().getInputStream();
        byte[] audioData = is.readAllBytes();
        assertThat(audioData[0]).isEqualTo(Byte.valueOf("20"));
        assertThat(audioData[1]).isEqualTo(Byte.valueOf("10"));
        assertThat(audio.getHeaders().size()).isEqualTo(2);
        assertThat(audio.getHeaders().get("Content-Length").get(0)).isEqualTo("2");
        assertThat(audio.getHeaders().get("Content-Type").get(0)).isEqualTo("audio/mpeg");
    }


    @Test
    public void should_call_audioService_addAudio() {
        // GIVEN
        Integer lessonId = 1;
        MultipartFile mockMultipartFile = Mockito.mock(MultipartFile.class);

        // WHEN
        testee.addAudio(lessonId, mockMultipartFile);

        // THEN
        Mockito.verify(audioService, Mockito.times(1)).addAudio(lessonId, mockMultipartFile);
    }

    @Test
    public void should_call_audioService_deleteAudio() {
        // GIVEN
        Integer lessonId = 1;

        // WHEN
        testee.deleteAudio(lessonId);

        // THEN
        Mockito.verify(audioService, Mockito.times(1)).deleteAudio(lessonId);
    }


}