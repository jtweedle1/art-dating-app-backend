package com.example.artforyourheart.controller;

import com.example.artforyourheart.model.Like;
import com.example.artforyourheart.model.User;
import com.example.artforyourheart.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @PostMapping
    public ResponseEntity<?> likeUser(@RequestBody Like like) {
        likesService.recordLike(like);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/matches")
    public ResponseEntity<List<User>> getMatches(@RequestParam String userId) {
        List<User> matches = likesService.findMatches(userId);
        return ResponseEntity.ok(matches);
    }
}
