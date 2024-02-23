package com.example.artforyourheart.repository;

import com.example.artforyourheart.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends MongoRepository<Like, String> {
    boolean existsByLikerIdAndLikeeId(String likerId, String likeeId);
    List<Like> findByLikerId(String likerId);
}
