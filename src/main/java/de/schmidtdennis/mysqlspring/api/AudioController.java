package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.AudioFile;
import de.schmidtdennis.mysqlspring.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AudioController {

    private final AudioService audioService;

    @Autowired
    public AudioController(AudioService audioService){
        this.audioService = audioService;
    }

    @GetMapping("audio/{lessonId}")
    public ResponseEntity<InputStreamResource> getAudio(@PathVariable("lessonId") Integer lessonid) throws IOException {

        AudioFile audio = audioService.getAudio(lessonid);

        if(audio != null){
            InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(audio.getData()));
            HttpHeaders headers = new HttpHeaders();
            System.out.println("Content-Length: " + audio.getData().length);
            headers.setContentLength(audio.getData().length);
            headers.set("Content-Type", "audio/mpeg");
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
        }

        return null;
    }

    @PostMapping(value = "audio/add/{lessonId}", consumes = { "multipart/form-data" })
    public void addAudio(@PathVariable("lessonId") Integer lessonId, @RequestBody MultipartFile file){
        audioService.addAudio(lessonId, file);
    }

    @DeleteMapping("audio/delete/{lessonId}")
    public void deleteAudio(@PathVariable("lessonId") Integer lessonId){
        audioService.deleteAudio(lessonId);
    }

}
