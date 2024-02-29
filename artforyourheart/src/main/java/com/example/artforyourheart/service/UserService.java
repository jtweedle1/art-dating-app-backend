package com.example.artforyourheart.service;


import com.example.artforyourheart.model.User;
import com.example.artforyourheart.repository.LikesRepository;
import com.example.artforyourheart.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //getOne
    //Optional handles possibility of not being able to find a particular user due to no match
    public Optional<User> findOneUser(String id) {
        return userRepository.findById(id);
    }

    //getAll
    public List<User> allUsers() {
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

    //put
    public User updateUser(ObjectId id, User updatedUser) {
        // set user ID to make sure it matches the existing user you want to update
        updatedUser.setId(id);
        // save updated user, overwriting the existing user with the same ID
        return userRepository.save(updatedUser);
    }

    //deleteOne
    public void deleteOneUser(String id) {
        userRepository.deleteById(id);
    }

    //login
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);

        //matches method checks if passwords match
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    //post register user
    public User createUser(String username, String password, String name, Integer age, String height, String location, String gender, String bio, String realPhoto, List<String> artPhotos, List<String> interests, List<String> matches, List<String> yes, List<String> no, List<String> roles) {
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(username, encodedPassword, name, age, height, location, gender, bio, realPhoto, artPhotos, interests, matches, yes, no, roles);
        User savedUser = userRepository.insert(user);
        return savedUser;
    }

}
