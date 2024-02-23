package com.example.artforyourheart.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.artforyourheart.model.User;
import com.example.artforyourheart.repository.LikesRepository;
import com.example.artforyourheart.repository.UserRepository;
import com.example.artforyourheart.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchingService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private UserService userService;

//    Optional<User> currentUserMatching = userService.findOneUser(userId);

    public List<User> findCompatibleMatches(String userId) {
        Optional<User> currentUserOptional = userService.findOneUser(userId);
        User currentUser = currentUserOptional.get();
        if (currentUser == null) {
            return List.of();
        }
        List<User> allUsers = userService.allUsers();
        return allUsers.stream()
                //removes self from string of people
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .collect(Collectors.toList());
    }
}
