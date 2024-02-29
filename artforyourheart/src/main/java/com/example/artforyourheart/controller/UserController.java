package com.example.artforyourheart.controller;


import com.example.artforyourheart.model.User;
import com.example.artforyourheart.repository.UserRepository;
import com.example.artforyourheart.service.LikesService;
import com.example.artforyourheart.service.UserService;
import com.example.artforyourheart.service.MatchingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LikesService likesService;

    @Autowired
    private MatchingService matchingService;

    // Get one user (by ID)
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable String id) {
        return userService.findOneUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.allUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        userService.deleteOneUser(id);
        return ResponseEntity.ok().build();
    }

    // Updated an existing user (please note that the server is expecting every field to not be null)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    // Register a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Map<String, Object> payload) {
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");
        String name = (String) payload.get("name");
        Integer age = (Integer) payload.get("age");
        String height = (String) payload.get("height");
        String location = (String) payload.get("location");
        String gender = (String) payload.get("gender");
        String bio = (String) payload.get("bio");
        String realPhoto = (String) payload.get("realPhoto");
        List<String> artPhotos = (List<String>) payload.get("artPhotos");
        List<String> interests = (List<String>) payload.get("interests");
        List<String> matches = (List<String>) payload.get("matches");
        List<String> yes = (List<String>) payload.get("yes");
        List<String> no = (List<String>) payload.get("no");
        List<String> roles = (List<String>) payload.get("roles");

        User user = userService.createUser(username, password, name, age, height, location, gender, bio, realPhoto, artPhotos, interests, matches, yes, no, roles);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    // Get the home screen and matchable users
    @GetMapping("/main")
    public ResponseEntity<List<User>> getUsersToSwipe(@RequestParam String userId) {
        Optional<User> currentUserOptional = userService.findOneUser(userId);
        User currentUser = currentUserOptional.get();
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<User> swipeableUsers = matchingService.findCompatibleMatches(userId);
        if (swipeableUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(swipeableUsers);
    }

    // Checks if user is authenticated; redundant now that we know it is implemented correctly (user is only authenticated if they successfully are logged in)
    @GetMapping("/api/auth/check")
    public ResponseEntity<?> checkAuthentication(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();

        return ResponseEntity.ok(isAuthenticated);
    }

}
