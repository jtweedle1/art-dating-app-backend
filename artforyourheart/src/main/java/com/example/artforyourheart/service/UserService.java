package com.example.artforyourheart.service;


import com.example.artforyourheart.model.User;
import com.example.artforyourheart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;









    //post register user
    public User createUser(Integer age, String name, String location, String gender, String art, String artPhotos, String photos, String height, List<String> matches, List<String> yes, List<String> no, String role, String bio){
        User user = new User(age, name, location, gender, art, artPhotos,photos, height ,matches, yes, no, role, bio);
        User savedUser = userRepository.insert(user);
        return savedUser;
    }

}
