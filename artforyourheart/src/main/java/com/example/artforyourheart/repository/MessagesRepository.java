package com.example.artforyourheart.repository;

import com.example.artforyourheart.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends MongoRepository<Message, String> {
    @Query("{$or: [{senderId: ?0, receiverId: ?1}, {senderId: ?1, receiverId: ?0}]}")
    List<Message> findMessagesBetweenTwoUsers(String userId1, String userId2);
}
