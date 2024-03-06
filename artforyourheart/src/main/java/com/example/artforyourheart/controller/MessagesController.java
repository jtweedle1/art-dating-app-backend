package com.example.artforyourheart.controller;

import com.example.artforyourheart.model.Message;
import com.example.artforyourheart.service.MessagesService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        message = messagesService.sendMessage(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{userId1}/{userId2}")
    public ResponseEntity<List<Message>> getMessagesBetweenUsers(@PathVariable String userId1, @PathVariable String userId2) {
        List<Message> messages = messagesService.getMessagesBetweenUsers(userId1, userId2);
        return ResponseEntity.ok(messages);
    }
}
