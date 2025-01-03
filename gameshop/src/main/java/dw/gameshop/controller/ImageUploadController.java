package dw.gameshop.controller;

import dw.gameshop.model.ImageFile;
import dw.gameshop.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageUploadController {

    @Autowired
    private ImageFileRepository imageFileRepository;

    @PostMapping("/upload/db")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            ImageFile imageFile = new ImageFile();
            imageFile.setFilename(file.getOriginalFilename());
            imageFile.setData(file.getBytes());
            imageFileRepository.save(imageFile);
            return "File uploaded to DB successfully: " + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
    }

    @GetMapping("/api/file/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        ImageFile imageFile = imageFileRepository.findByFilename(filename);
        return new ResponseEntity<>(imageFile.getData(), HttpStatus.OK);
    }
}