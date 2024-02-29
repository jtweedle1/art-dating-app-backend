package com.example.artforyourheart.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import com.example.artforyourheart.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;
    private final UserRepository userRepository;

    public CloudinaryService(Cloudinary cloudinary, UserRepository userRepository) {
        this.cloudinary = cloudinary;
        this.userRepository = userRepository;
    }

    // For the user's "real" profile photo
    public String uploadRealPhoto(MultipartFile file, String userId) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String realPhotoUrl = (String) uploadResult.get("url");
        userRepository.findById(userId).ifPresent(user -> {
            user.setRealPhoto(realPhotoUrl);
            userRepository.save(user);
        });
        return realPhotoUrl;
    }

    // For the user's multiple art photos
    public List<String> uploadArtPhotos(List<MultipartFile> files, String userId) throws IOException {
        List<String> artPhotoUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) uploadResult.get("url");
            artPhotoUrls.add(url);
        }
        // Update user's art photos URLs
        userRepository.findById(userId).ifPresent(user -> {
            user.getArtPhotos().addAll(artPhotoUrls);
            userRepository.save(user);
        });
        return artPhotoUrls;
    }
}