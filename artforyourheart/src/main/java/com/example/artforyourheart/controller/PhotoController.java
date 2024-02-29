package com.example.artforyourheart.controller;
import com.example.artforyourheart.cloudinary.CloudinaryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    private final CloudinaryService cloudinaryService;

    public PhotoController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/upload")
    public Map uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        return cloudinaryService.upload(file);
    }

}
