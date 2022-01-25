package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.AudioFile;
import de.schmidtdennis.mysqlspring.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class AudioController {

    private final AudioService audioService;

    @Autowired
    public AudioController(AudioService audioService){
        this.audioService = audioService;
    }

    @GetMapping("audio/{lessonId}")
    public AudioFile getAudio(@PathVariable("lessonId") Integer lessonid) {
        return audioService.getAudio(lessonid);
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
